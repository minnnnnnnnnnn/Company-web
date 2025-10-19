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

        if (!name || ! teamId || !role || !joinDate || !birthDate) {
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

            fetch('http://localhost:8080/employee')
                .then(response => response.json())
                .then(data => {
                    startSelect.innerHTML = '<option value="">이름</option>'; // 기본 옵션
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


    // 출근 처리
    document.getElementById('startBtn').addEventListener('click', function() {
        const employeeId = document.getElementById('startSelect').value;

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
            document.getElementById('startSelect').value = ''; // 선택 초기화
        })
        .catch(error => {
            console.error(error);
            alert('출근 처리 중 오류가 발생했습니다.');
        });
    });

});
