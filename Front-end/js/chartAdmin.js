const formulario = document.querySelector("form");
const selectProdutoFiltrado = document.getElementById("produto-filter");
const selectVendedorFiltrado = document.getElementById("vendedor-filter");

fetch("http://localhost:8080/produto")
    .then(response => response.json())
    .then(data => {
        data.forEach(produto => {
            const option = document.createElement('option');
            option.text = produto.nome;
            selectProdutoFiltrado.appendChild(option);
        });


fetch("http://localhost:8080/usuario")
    .then(response => response.json())
    .then(data => {
        data.forEach(usuario => {
            const option = document.createElement('option');
            option.text = usuario.nome;
            select.appendChild(option);
        });
    })
    .catch(error => console.error(error));


fetch("http://localhost:8080/usuario")
    .then(response => response.json())
    .then(data => {
    data.forEach(usuario => {
            const option = document.createElement('option');
            option.text = usuario.nome;
            selectVendedorFiltrado.appendChild(option);
        });

fetch('http://localhost:8080/venda/topVendedores')
  .then(function(response) {
    return response.json();
  })
  .then(function(data) {
    var dados = data.map(function(item) {
      return { y: item.nome_usuario, a: item.total_vendido};
    });

    var config = {
      data: dados,
      xkey: 'y',
      ykeys: 'a',
      labels: ['Total Income', 'Total Outcome'],
      fillOpacity: 0.6,
      hideHover: 'auto',
      behaveLikeLine: true,
      resize: true,
      pointFillColors:['#ffffff'],
      pointStrokeColors: ['black'],
      lineColors:['blue']
    };

    config.element = 'stacked';
    config.stacked = true;
    Morris.Bar(config);
  })
  .catch(function(error) {
    console.log(error);
  });

function AdminFiltro(){
  fetch('http://localhost:8080/venda/topVendedores')
  .then(function(response) {
    return response.json();
  })
  .then(function(data) {
    var dados = data.map(function(item) {
      return { y: item.nome_usuario, a: item.total_vendido};
    });

    var config = {
      data: dados,
      xkey: 'y',
      ykeys: 'a',
      labels: ['Total Income', 'Total Outcome'],
      fillOpacity: 0.6,
      hideHover: 'auto',
      behaveLikeLine: true,
      resize: true,
      pointFillColors:['#ffffff'],
      pointStrokeColors: ['black'],
      lineColors:['blue']
    };

    config.element = 'stacked';
    config.stacked = true;
    Morris.Bar(config);
  })
  .catch(function(error) {
    console.log(error);
  });
};