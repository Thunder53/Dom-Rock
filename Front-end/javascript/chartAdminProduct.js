// fetch("http://localhost:8080/venda/topProdutos")
//   .then(function (response) {
//     return response.json();
//   })
//   .then(function (data) {
//     var dados = data.map(function (item) {
//       return { y: item.nome_produto, a: item.total_vendido };
//     });

//     var config = {
//       data: dados,
//       xkey: "y",
//       ykeys: "a",
//       labels: ["Total Income", "Total Outcome"],
//       fillOpacity: 0.6,
//       hideHover: "auto",
//       behaveLikeLine: true,
//       resize: true,
//       pointFillColors: ["#ffffff"],
//       pointStrokeColors: ["black"],
//       lineColors: ["blue"],
//     };

//     config.element = "stacked";
//     config.stacked = true;
//     Morris.Bar(config);
//   })
//   .catch(function (error) {
//     console.log(error);
//   });
