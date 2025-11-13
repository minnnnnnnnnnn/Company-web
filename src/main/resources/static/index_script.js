document.addEventListener('DOMContentLoaded', function() {

    // 팀 조회
    const teamSelect = document.getElementById('teamSelect');

    fetch('http://localhost:8080/team')
        .then(response => response.json())
        .then(data => {
            teamSelect.innerHTML = '<option value="">소속 팀 이름</option>'; // 기본 옵션
            data.forEach(team => {
                const option = document.createElement('option');
                option.value = team.id;
                option.textContent = team.name;
                teamSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('팀 목록 불러오기 오류:', error);
        });

    // 팀 등록
    document.getElementById('registerTeamBtn').addEventListener('click', function() {
        const name = document.querySelector('input[placeholder="팀 이름"]').value;
        if (!name) {
            alert('이름을 입력해주세요.');
            return;
        }

        fetch('http://localhost:8080/team', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name: name })
        })
        .then(response => {
            if (!response.ok) throw new Error('팀 등록 실패');
            alert('팀 등록 성공!');
            document.querySelector('input[placeholder="팀 이름"]').value = '';
        })
        .catch(error => {
            console.error(error);
            alert('팀 등록 중 오류가 발생했습니다.');
        });
    });

    // 직원 등록
    document.getElementById('registerEmployeeBtn').addEventListener('click', function() {
        const name = document.querySelector('input[placeholder="직원 이름"]').value;
        const teamId = document.getElementById('teamSelect').value;
        const role = document.getElementById('roleSelect').value;
        const joinDate = document.getElementById('joinDate').value;
        const birthDate = document.getElementById('birthDate').value;

        if (!name || !teamId || !role || !joinDate || !birthDate) {
            alert('모든 항목을 입력해주세요.');
            return;
        }

        fetch('http://localhost:8080/employee', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, teamId, role, joinDate, birthDate })
        })
        .then(response => {
            if (!response.ok) throw new Error('직원 등록 실패');
            alert('직원 등록 성공!');
            document.querySelector('input[placeholder="직원 이름"]').value = '';
            document.getElementById('teamSelect').value = '';
            document.getElementById('roleSelect').value = '';
            document.getElementById('joinDate').value = '';
            document.getElementById('birthDate').value = '';
        })
        .catch(error => {
            console.error(error);
            alert('직원 등록 중 오류가 발생했습니다.');
        });
    });

    // 직원 조회
    const startSelect = document.getElementById('startSelect');
    const endSelect = document.getElementById('endSelect');

    // 1. 전체 직원 조회 (startSelect)
    fetch('http://localhost:8080/employee')
        .then(response => response.json())
        .then(data => {
            startSelect.innerHTML = '<option value="">이름</option>';
            data.forEach(employee => {
                const option = document.createElement('option');
                option.value = employee.id;
                option.textContent = employee.name;
                startSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('직원 목록 불러오기 오류:', error);
        });

    // 2. 출근 중 직원 조회 (endSelect 초기화)
    fetch('http://localhost:8080/employee/checkIn')
        .then(response => response.json())
        .then(data => {
            endSelect.innerHTML = '<option value="">이름</option>';
            data.forEach(employee => {
                const option = document.createElement('option');
                option.value = employee.id;
                option.textContent = employee.name;
                endSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('출근 직원 목록 불러오기 오류:', error);
        });

    // 출근 처리
    document.getElementById('startBtn').addEventListener('click', function() {
        const employeeId = startSelect.value;

        if (!employeeId) {
            alert('직원을 선택해주세요.');
            return;
        }

        fetch('http://localhost:8080/employee/checkIn', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ employeeId: Number(employeeId) })
        })
        .then(response => {
            if (!response.ok) throw new Error('출근 처리 실패');
            alert('출근 처리 완료!');
            startSelect.value = ''; // 선택 초기화
            addEmployeeToEndSelect(employeeId);
        })
        .catch(error => {
            console.error(error);
            alert('출근 처리 중 오류가 발생했습니다.');
        });
    });

    // 퇴근 처리
    document.getElementById('endBtn').addEventListener('click', function() {
        const employeeId = endSelect.value;

        if (!employeeId) {
            alert('직원을 선택해주세요.');
            return;
        }

        fetch('http://localhost:8080/employee/checkOut', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ employeeId: Number(employeeId) })
        })
        .then(response => {
            if (!response.ok) throw new Error('퇴근 처리 실패');
            alert('퇴근 처리 완료!');
            removeEmployeeFromEndSelect(employeeId);
        })
        .catch(error => {
            console.error(error);
            alert('퇴근 처리 중 오류가 발생했습니다.');
        });
    });

    // 출근한 직원 endSelect에 추가
    function addEmployeeToEndSelect(employeeId) {
        // startSelect에서 이름 가져오기
        const option = startSelect.querySelector(`option[value="${employeeId}"]`);
        if (!option) return;

        // 이미 endSelect에 없으면 추가
        if (!endSelect.querySelector(`option[value="${employeeId}"]`)) {
            const newOption = document.createElement('option');
            newOption.value = employeeId;
            newOption.textContent = option.textContent;
            endSelect.appendChild(newOption);
        }
    }

    // 퇴근 처리 후 endSelect에서 제거
    function removeEmployeeFromEndSelect(employeeId) {
        const option = endSelect.querySelector(`option[value="${employeeId}"]`);
        if (option) endSelect.removeChild(option);
    }

});
