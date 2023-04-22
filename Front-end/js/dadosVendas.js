$.ajax({
    url: 'http://localhost:8080/venda',
    type: 'GET',
    success: function(result) {
        var labels = [];
        var data = [];

        for (var i = 0; i < result.length; i++) {
            labels.push(result[i].atualizada_em);
            data.push(result[i].quant_estimada);
        }

        var ctx = document.getElementById('myChart').getContext('2d');
        var chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Quantidade Estimada',
                    data: data,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                }
            }
        });
    },
    error: function(error) {
        console.log(error);
    }
});
