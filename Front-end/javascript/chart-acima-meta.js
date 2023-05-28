fetch("http://localhost:8080/venda/acima-meta")
.then(function (response) {
    return response.json();
  })
  .then(function (data) {
    var dados = data.map(function (item) {
      return { y: item.quant_vendida, a: item.fk_usuario_id };
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

