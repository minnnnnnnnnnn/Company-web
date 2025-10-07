document.addEventListener('DOMContentLoaded', function() {

    // 팀 조회
    fetch('http://localhost:8080/team')
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector('#teamTable tbody');
            data.forEach(team => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${team.name || ''}</td>
                    <td>${team.managerName || ''}</td>
                    <td>${team.memberCount || 0}</td>
                `;
                tbody.appendChild(row);
            });
        });

    // 직원 조회
    fetch('http://localhost:8080/employee')
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector('#employeeTable tbody');
            data.forEach(emp => {
                const birth = emp.birthDate ? emp.birthDate.split('T')[0] : '';
                const join = emp.joinDate ? emp.joinDate.split('T')[0] : '';

                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${emp.name || ''}</td>
                    <td>${emp.teamName || ''}</td>
                    <td>${emp.position || ''}</td>
                    <td>${birth}</td>
                    <td>${join}</td>
                `;
                tbody.appendChild(row);
            });
        });

});
