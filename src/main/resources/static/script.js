document.getElementById('registerEmployeeBtn').addEventListener('click', function() {
    // 입력 값 가져오기
    const name = document.querySelector('input[placeholder="직원 이름"]').value;
    const role = document.querySelector('select').value;
    const joinDate = document.getElementById('joinDate').value;
    const birthDate = document.getElementById('birthDate').value;

    // 간단한 유효성 체크
    if (!name || !role || !joinDate || !birthDate) {
        alert('모든 항목을 입력해주세요.');
        return;
    }

    // API 요청
    fetch('http://localhost:8080/employee', {  // 여기에 실제 API 주소 입력
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name,
            role: role,
            joinDate: joinDate,
            birthDate: birthDate
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('직원 등록 실패');
        }
    })
    .then(data => {
        alert('직원 등록 성공!');
        console.log(data);
        // 입력 폼 초기화
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