document.addEventListener('DOMContentLoaded', function() {

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
        const role = document.querySelector('select').value;
        const joinDate = document.getElementById('joinDate').value;
        const birthDate = document.getElementById('birthDate').value;

        if (!name || !role || !joinDate || !birthDate) {
            alert('모든 항목을 입력해주세요.');
            return;
        }

        fetch('http://localhost:8080/employee', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, role, joinDate, birthDate })
        })
        .then(response => {
            if (!response.ok) throw new Error('직원 등록 실패');
            alert('직원 등록 성공!');
            document.querySelector('input[placeholder="직원 이름"]').value = '';
            document.querySelector('select').value = '';
            document.getElementById('joinDate').value = '';
            document.getElementById('birthDate').value = '';
        })
        .catch(error => {
            console.error(error);
            alert('직원 등록 중 오류가 발생했습니다.');
        });
    });

});
