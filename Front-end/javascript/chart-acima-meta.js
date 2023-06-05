fetch("http://localhost:8080/venda/acima-meta")
.then(function (response) {
    return response.json();
  })
  .then(function (data) {
    fetch("http://localhost:8080/usuario/vendedores")
    .then(function (response){
      return response.json();
    })
    .then(function(vendedores){

      var linha_tabela = [];

      for (var lista = 0; lista<data.length; lista++){ 
        for (var id = 0; id<vendedores.length; id++){ 
          if (data[lista].fk_usuario_id == vendedores[id].id){
            linha_tabela.push({y: vendedores[id].nome, a: data[lista].quant_vendida})
          }
        }
      }

      
      var dados = linha_tabela 
  
      var config = {
        data: dados,
        xkey: "y",
        ykeys: "a",
        labels: ["Total"],
        fillOpacity: 0.6,
        hideHover: "auto",
        behaveLikeLine: false,
        pointSize: 100,
        resize: true,
        pointFillColors: ["#ffffff"],
        pointStrokeColors: ["black"],
        lineColors: ["blue"],
         xLabelAngle: 45, // Ângulo de rotação da legenda no eixo x
      };
  
      config.element = "vendedoresAcimaMeta";
      config.stacked = false;
      Morris.Bar(config);
    
    });
  })
  .catch(function (error) {
    console.log(error);
  });

