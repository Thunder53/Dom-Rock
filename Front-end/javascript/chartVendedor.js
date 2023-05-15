fetch("http://localhost:8080/venda/topVendedores")
  .then(function (response) {
    return response.json();
  })
  .then(function (data) {
    var dados = data.map(function (item) {
      return { y: item.nome_usuario, a: item.total_vendido };
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
    };

    config.element = "stacked";
    config.stacked = true;
    Morris.Bar(config);
  })
  .catch(function (error) {
    console.log(error);
  });

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
    };

    config.element = "produtosrank";
    config.stacked = true;
    Morris.Bar(config);
  })
  .catch(function (error) {
    console.log(error);
  });

