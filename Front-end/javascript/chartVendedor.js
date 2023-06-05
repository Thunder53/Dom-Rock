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
      xLabelAngle: 45, // Ângulo de rotação da legenda no eixo x
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
      xLabelAngle: 45, // Ângulo de rotação da legenda no eixo x
    };

    config.element = "produtosrank";
    config.stacked = true;
    Morris.Bar(config);
  })
  .catch(function (error) {
    console.log(error);
  });

  fetch(`http://localhost:8080/venda/verificar-quantidades/${localStorage.getItem('id')}`)
  .then(function (response) {
    return response.json();
  })
  .then(function (data) {
    var item = data;
    var successAlert = document.getElementById("sucess");
    var dangerAlert = document.getElementById("danger");

    if (item === true) {
      successAlert.classList.add("hidden");
      dangerAlert.classList.remove("hidden");
    } else {
      successAlert.classList.remove("hidden");
      dangerAlert.classList.add("hidden");
    }
  })
  .catch(function (error) {
    console.log(error);
  });
  

  fetch(`http://localhost:8080/venda/vendedorHistorico/${localStorage.getItem('id')}`)
  .then(function (response) {
    return response.json();
  })
  .then(function (data) {
    console.log(data)
    var dados = data.map(function (item) {
      return { y: item.quant_estimada, a: item.quant_vendida };
    });

    var config = {
      data: dados,
      xkey: "y",
      ykeys: "a",
      labels: ["Quantidade estimada","Quantidade vendida"],
      fillOpacity: 0.6,
      hideHover: "auto",
      behaveLikeLine: true,
      resize: true,
      pointFillColors: ["#ffffff"],
      pointStrokeColors: ["black"],
      lineColors: ["blue"],
      xLabelAngle: 45, // Ângulo de rotação da legenda no eixo x
    };

    config.element = "historico";
    config.stacked = true;
    Morris.Bar(config);
  })
  .catch(function (error) {
    console.log(error);
  });

  
