// Obtenha os dados do backend do Spring Boot (usando o fetch API, por exemplo)
fetch('http://localhost:8080/venda')
  .then(response => {
    return response.json();
  })
  .then(data => {
    const quantidadesEstimadas = data.map(venda => venda.quant_estimada / 100000000000000);
    const quantidadesVendidas = data.map(venda => venda.quant_vendida / 100000000000000);
    const labels = data.map(venda => new Date(venda.atualizada_em));

    const quantidadesEstimadaslinha = data.map(venda => venda.quant_estimada / 100000000000000);
    const quantidadesVendidaslinha = data.map(venda => venda.quant_vendida / 100000000000000);
    const labels_linha = data.map(venda => new Date(venda.atualizada_em));
    
    // Dados para o gráfico de barras
    const dataEstimada = {
      label: 'Quantidade Estimada',
      data: quantidadesEstimadas,
      backgroundColor: 'rgba(54, 162, 235, 0.2)',
      borderColor: 'rgba(54, 162, 235, 1)',
      borderWidth: 1
    };

    const dataVendida = {
      label: 'Quantidade Vendida',
      data: quantidadesVendidas,
      backgroundColor: 'rgba(75, 192, 192, 0.2)',
      borderColor: 'rgba(75, 192, 192, 1)',
      borderWidth: 1
    };

    const barData = {
      labels: labels.map(label => label.toLocaleDateString()), // converte as labels para string
      datasets: [dataEstimada, dataVendida]
    };

    // Dados para o gráfico de linha
    const dataEstimadalinha = {
      labels : 'Quantidade Estimada',
      data: quantidadesEstimadaslinha,
      backgroundColor: 'rgba(54, 162, 235, 0.2)',
      borderColor: 'rgba(54, 162, 235, 1)',
      borderWidth: 1
  
    };
    const dataVendidalinha = {
      label: 'Quantidade Vendida',
      data: quantidadesVendidaslinha,
      backgroundColor: "rgba(75, 192, 192, 1)",
      borderColor: "rgba(75, 192, 192, 1)",
      borderWidth: 1
    };
    const lineData = {
      labels: labels.map(label => label.toLocaleDateString()), // converte as labels para string
      datasets: [dataEstimadalinha, dataVendidalinha]
    };

    //Criar grafico de area
    const dataEstimadaarea = {
      labels : 'Quantidade Estimada',
      data: quantidadesEstimadaslinha,
      backgroundColor: 'rgba(75, 192, 192, 0.2)',
      borderColor: 'rgba(75, 192, 192, 1)',
      borderWidth: 1
  
    };
    const dataVendidaarea = {
      label: 'Quantidade Vendida',
      data: quantidadesVendidaslinha,
      backgroundColor: 'rgba(54, 162, 235, 0.2)',
      borderColor: 'rgba(54, 162, 235, 1)',
      borderWidth: 1
    };
    
    const areadata = {
      labels: labels.map(label => label.toLocaleDateString()), // converte as labels para string
      datasets: [dataEstimadaarea, dataVendidaarea] // adicione o novo conjunto de dados
    };

    const options = {
      scales: {
        yAxes: [
          {
            ticks: {
              beginAtZero: false
            }
          }
        ]
      }
    };

    // Crie as instâncias de gráfico
    const barChart = new Chart(document.getElementById('graficos_barra'), {
      type: 'bar',
      data: barData
    });

    const lineChart = new Chart(document.getElementById('graficos_barra_linha'), {
      type: 'line',
      data: lineData
    });

    const areaChart = new Chart(document.getElementById('grafico_area'), {
      type: 'line',
      data: areadata,
      options: options
    });
});
