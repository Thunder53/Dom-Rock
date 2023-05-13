const view_cliente = document.getElementById("view_cliente");
view_cliente.addEventListener("click", function() {
  window.location.href = "../view/formVisualizacaoCliente.html";
});

const cadastro_cliente    = document.getElementById("cadastro_cliente");
cadastro_cliente.addEventListener("click", function() {
  window.location.href = "formCliente.html";
});

const cadastro_vendedor = document.getElementById("cadastro_vendedor");
cadastro_vendedor.addEventListener("click", function() {
  window.location.href = "formVendedor.html";
});

const dados = document.getElementById("dados");
dados.addEventListener("click", function() {
  window.location.href = "../view/adminCharts.html";
});