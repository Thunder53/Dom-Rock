const select = document.getElementById('filtro-produto');
const filtroButton = document.getElementById("filtro-button");
let vendaChart;
let vendedoresChart;
let produtosChart;

filtroButton.addEventListener("click", filtro);

function generateVendedoresChart() {
  fetch("http://localhost:8080/venda/topVendedores")
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      var dados = data.map(function (item) {
        return { y: item.nome_usuario, a: item.total_vendido, nome: item.nome_completo };
      });

      var config = {
        data: dados,
        xkey: "y",
        ykeys: "a",
        labels: ["Total"],
        fillOpacity: 0.6,
        hideHover: "auto",
        behaveLikeLine: true,
        resize: true,
        pointFillColors: ["#ffffff"],
        pointStrokeColors: ["black"],
        lineColors: ["blue"],
        xLabelAngle: 45,
      };

      config.element = "stackedVendedores";
      config.stacked = true;
      Morris.Bar(config);
    })
    .catch(function (error) {
      console.log(error);
    });
}


function generateProdutosChart() {
  fetch("http://localhost:8080/produto/topProdutos")
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      var dados = data.map(function (item) {
        return { y: item.nome_produto, a: item.total_vendido };
      });

      var config = {
        data: dados,
        xkey: "y",
        ykeys: "a",
        labels: ["Total"],
        fillOpacity: 0.6,
        hideHover: "auto",
        behaveLikeLine: true,
        resize: true,
        pointFillColors: ["#ffffff"],
        pointStrokeColors: ["black"],
        lineColors: ["blue"],
        xLabelAngle: 45,
      };

      config.element = "stackedProdutos";
      config.stacked = true;
      Morris.Bar(config);
    })
    .catch(function (error) {
      console.log(error);
    });
}

function generateVendaChart() {
  fetch("http://localhost:8080/venda")
    .then(response => response.json())
    .then(data => {
      const labels = data.map(item => {
        const date = new Date(item.criada_em);
        const formattedDate = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
        return formattedDate;
      });

      const totalEstimado = data.map(item => item.quant_estimada);
      const totalVendido = data.map(item => item.quant_vendida);

      const config = {
        type: "bar",
        data: {
          labels: labels,
          datasets: [{
            label: "Total Estimado",
            data: totalEstimado,
            backgroundColor: "rgba(0, 0, 255, 0.6)",
            borderColor: "black",
            borderWidth: 1,
          },
          {
            label: "Total Vendido",
            data: totalVendido,
            backgroundColor: "rgba(255, 0, 0, 0.6)",
            borderColor: "black",
            borderWidth: 1,
          }],
        },
        options: {
          responsive: true,
          scales: {
            x: {
              grid: {
                display: false,
              },
            },
            y: {
              grid: {
                display: true,
              },
              beginAtZero: true,
            },
          },
        },
      };

      const ctx = document.getElementById("bar-chart").getContext("2d");
      vendaChart = new Chart(ctx, config);
    })
    .catch(error => console.log(error));
}

function initPage() {
  generateVendedoresChart();
  generateProdutosChart();
  generateVendaChart();
}

initPage();

async function filtro() {
  const produtoNome = select.value;

  if (produtoNome === "Todos os produtos") {
    initPage();
    return;
  }

  const produtoResponse = await fetch(`http://localhost:8080/produto/id-por-nome?nome_produto=${produtoNome}`);
  const produto = await produtoResponse.json();

  fetch(`http://localhost:8080/venda/filtro-produto/${produto}`)
    .then(response => response.json())
    .then(data => {
      const labels = data.map(item => item.criada_em);
      const totalEstimado = data.map(item => item.quant_estimada);
      const totalVendido = data.map(item => item.quant_vendida);

      vendaChart.data.labels = labels;
      vendaChart.data.datasets[0].data = totalEstimado;
      vendaChart.data.datasets[1].data = totalVendido;
      vendaChart.update();
    })
    .catch(error => console.log(error));

  const barChartCanvas = document.getElementById("bar-chart");
  barChartCanvas.innerHTML = "";
  vendaChart.destroy();
  const ctx = barChartCanvas.getContext("2d");
  vendaChart = new Chart(ctx, { type: "bar" });
}

fetch("http://localhost:8080/produto/produto")
  .then(response => response.json())
  .then(data => {
    data.forEach(produto => {
      const option = document.createElement('option');
      option.text = produto.nome_produto;
      select.appendChild(option);
    });
  })
  .catch(error => console.error(error));
