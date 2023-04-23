fetch('http://localhost:8080/venda')
    .then(response => response.json())
    .then(data => {
        const quantidades = data.map(venda => venda.quant_estimada / 100000000000);
        const labels = data.map(venda => venda.atualizada_em);
        const ctx = document.getElementById('myChart').getContext('2d');
        const chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Quantidade Estimada',
                    data: quantidades,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{}]
                }
            }
        });
    })
    .catch(error => {
        console.error(error);
    });
