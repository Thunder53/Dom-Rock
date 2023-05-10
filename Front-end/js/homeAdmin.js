const botao_cliente = document.getElementById("#botao");
botao_cliente.addEventListener("click", function() {
  window.location.href = "../view/formVisualizacaoCliente.html";
});

const botao_dados    = document.getElementById("dados");
botao_dados.addEventListener("click", function() {
  window.location.href = "dadosVendas.html";
});

const botao_vendedor = document.getElementById("vendedor");
botao_vendedor.addEventListener("click", function() {
  window.location.href = "formVendedor.html";
});

