const formulario = document.querySelector("form");
const selectProduto = document.getElementById("produto-filter");
const selectVendedor = document.getElementById("vendedor-filter");

fetch("http://localhost:8080/produto")
    .then(response => response.json())
    .then(data => {
        data.forEach(produto => {
            const option = document.createElement('option');
            option.text = produto.nome_produto;
            selectProduto.appendChild(option);
        });
    })
    .catch(error => console.error(error));

fetch("http://localhost:8080/usuario")
    .then(response => response.json())
    .then(data => {
        data.forEach(usuario => {
            
            const option = document.createElement('option');
            option.text = usuario.nome;
            selectVendedor.appendChild(option);
        });
    })
    .catch(error => console.error(error));

function BotaoData(){
    select.getElementsByTagName(Janeiro);
    select.getElementsByTagName(Feveiro);
    select.getElementsByTagName(Março);
    select.getElementsByTagName(Abril);
    select.getElementsByTagName(Maio);
    select.getElementsByTagName(Junho);
    select.getElementsByTagName(Julho);
    select.getElementsByTagName(Agosto);
    select.getElementsByTagName(Setembro);
    select.getElementsByTagName(Outubro);
    select.getElementsByTagName(Novembro);
    select.getElementsByTagName(Dezembro);
}

function BotaoProduto(){
    select.getElementsById('produto-filter');

    fetch('http://localhost:8080/filtro/produto')
  .then(function(response) {
    return response.json();
  })
  .then(function(data) {
    var dados = data.map(function(item) {
      return { y: item.produto_filtrado, a: item.total_vendido};
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

