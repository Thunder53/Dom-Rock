fetch('http://localhost:8080/produto-com-venda')
  .then(response => response.json()) // <-- Converte a resposta para um objeto JavaScript
  .then(data => {
    
    const quant_vendida = data.map(venda => venda.quant_vendida / 100000000000000)
    const nome_produto = data.map(produto => produto.nome_produto);
    const diasDaSemana = ['Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado', 'Domingo'];
    const labels = diasDaSemana.map(dia => new Date(`May ${dia} 2023`));

    // Bar chart data
    const dataEstimada = {
      label: 'nome_produto',
      data: quant_vendida,
      fill: false,
      backgroundColor: 'rgba(54, 162, 235, 0.2)',
      borderColor: 'rgba(54, 162, 235, 1)',
      borderWidth: 1,
      yAxisID: 'y-axis-1'
    };
    
    const dataVendida = {
      label: 'aaa',
      data: nome_produto,
      backgroundColor: 'rgba(75, 192, 192, 0.2)',
      borderColor: 'rgba(75, 192, 192, 1)',
      borderWidth: 1,
      yAxisID: 'y-axis-2'
    };
    
    const barData = {
      labels: labels.map(label => label.toLocaleDateString()),
      datasets: [dataEstimada, dataVendida]
    };
    
    const barChart = new Chart(document.getElementById('myChart'), {
      type: 'bar',
      data: barData,
      options: {
        scales: {
          yAxes: [{
            id: 'y-axis-1',
            position: 'left',
            ticks: {
              beginAtZero: true
            }
          }, {
            id: 'y-axis-2',
            position: 'right',
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    });
});
