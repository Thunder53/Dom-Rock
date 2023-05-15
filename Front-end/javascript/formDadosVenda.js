fetch('http://localhost:8080/venda')
  .then(response => response.json())
  .then(data => {

    const quantidadesEstimadas = data.map(venda => venda.quant_estimada / 100000000000000);
    const quantidadesVendidas = data.map(venda => venda.quant_vendida / 100000000000000);
    const labels = data.map(venda => new Date(venda.atualizada_em));

    labels.sort((a, b) => a - b);


  const ctx = document.getElementById("myChart").getContext("2d");

    const chart = new Chart(ctx, {
    type: "bar",
    data: {
        labels: labels.map(label => label.toLocaleDateString()),
        datasets: [
        {   
                label: "Quantidade Estimada",
                data: quantidadesEstimadas,
                type: "line",
                fill: false,
                borderColor: "white",
                borderWidth: 3,
                
        },
        {
            label: "Quantidade Vendida",
            data: quantidadesVendidas,
            backgroundColor: "rgba(75, 192, 192, 1)",
            borderColor: "rgba(75, 192, 192, 1)",
            borderWidth: 1,
        },
        ],
    },
    options: {
        scales: {
          yAxes: [{
            ticks: {
              callback: function (value, index, ticks) {
                return (value / 1000).toFixed(0) + 'K';
              }
            },
            scaleLabel: {
              display: true,
              labelString: 'Quantidade (em trilhões)',
              fontColor: '#FFFFFF'
            }
          }]
        }
      },
    });
})
.catch(error => {
  console.error(error);
});
