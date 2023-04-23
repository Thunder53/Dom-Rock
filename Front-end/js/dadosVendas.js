fetch('http://localhost:8080/venda')
  .then(response => response.json())
  .then(data => {
    const quantidadesEstimadas = data.map(venda => venda.quant_estimada / 100000000000);
    const quantidadesVendidas = data.map(venda => venda.quant_vendida / 100000000000);
    const labels = data.map(venda => venda.atualizada_em);
    const ctx = document.getElementById('myChart').getContext('2d');
    const chart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [{
          label: 'Quantidade Estimada',
          data: quantidadesEstimadas,
          backgroundColor: 'rgba(54, 162, 235, 0.2)',
          borderColor: 'rgba(54, 162, 235, 1)',
          borderWidth: 1
        }, {
          label: 'Quantidade Vendida',
          data: quantidadesVendidas,
          backgroundColor: 'rgba(255, 99, 132, 0.2)',
          borderColor: 'rgba(255, 99, 132, 1)',
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          yAxes: [{
            ticks: {
              callback: function (value, index, ticks) {
                return (value / 1000).toFixed(0) + 'K';
              }
            }
          }]
        }
      }
    });
  })
  .catch(error => {
    console.error(error);
  });
