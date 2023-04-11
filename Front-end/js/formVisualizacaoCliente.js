

const formulario = document.querySelector("form");
const Icod_cliente = document.querySelector(".cod_cliente");
const Inome_cliente = document.querySelector(".nome_cliente");
const Inome_gerencia = document.querySelector(".nome_gerencia");
function buscar(){
    fetch("http://localhost:8080/clientes",
    {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "GET",
        success: function(data) {
          // Atualize os elementos HTML com os dados recebidos
          ('.info_clientes td:eq(0)').text(data.cod_cliente);
          ('.info_clientes td:eq(1)').text(data.nome_cliente);
          ('.info_clientes td:eq(2)').text(data.nome_gerencia);
        },
    })
    .then(function(res) {console.log(res)})
    .catch(function(res) {console.log(res)})
};