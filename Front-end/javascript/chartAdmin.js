const select = document.getElementById('filtro-produto');
const filtroButton = document.getElementById("filtro-button");

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
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      var dados = data.map(function (item) {
        return { y: item.criada_em, a: item.quant_estimada, b: item.quant_vendida};
      });

      var xLabels = data.map(function (item) {
        return item.criada_em;
      });

      var config = {
        data: dados,
        xkey: "y",
        ykeys: ["a", "b"],
        labels: ["Total Estimado", "Total Vendido"],
        xLabels: xLabels,
        fillOpacity: 0.6,
        hideHover: "auto",
        behaveLikeLine: true,
        resize: true,
        pointFillColors: ["#ffffff"],
        pointStrokeColors: ["black"],
        lineColors: ["blue"],
        xLabelAngle: 45,
      };

      config.element = "bar-chart";
      config.Bar = true;
      Morris.Bar(config);
    })
    .catch(function (error) {
      console.log(error);
    });
}


function initPage() {
  generateVendedoresChart();
  generateProdutosChart();
  generateVendaChart();
}

initPage();

async function filtro(){
  const produtoNome = select.value;

  if (produtoNome === "Todos os produtos") {
    initPage();
    return;
  }

  const produtoResponse = await fetch(`http://localhost:8080/produto/id-por-nome?nome_produto=${produtoNome}`);
  const produto = await produtoResponse.json();

  fetch(`http://localhost:8080/venda/filtro-produto/${produto}`)
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      var dados = data.map(function (item) {
        return { y: item.criada_em, a: item.quant_estimada, b: item.quant_vendida};
      });

      var xLabels = data.map(function (item) {
        return item.criada_em;
      });

      var config = {
        data: dados,
        xkey: "y",
        ykeys: ["a", "b"],
        labels: ["Total Estimado", "Total Vendido"],
        xLabels: xLabels,
        fillOpacity: 0.6,
        hideHover: "auto",
        behaveLikeLine: true,
        resize: true,
        pointFillColors: ["#ffffff"],
        pointStrokeColors: ["black"],
        lineColors: ["blue"],
        xLabelAngle: 45,
      };

      config.element = "bar-chart";
      config.Bar = true;
      Morris.Bar(config);
    })

  document.getElementById("bar-chart").innerHTML = "";
  Morris.Bar({ element: 'bar-chart'});

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

