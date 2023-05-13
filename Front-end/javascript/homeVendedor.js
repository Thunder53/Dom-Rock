const view_cliente = document.getElementById("vendas");
view_cliente.addEventListener("click", function() {
  window.location.href = "formVendas.html";
});

const cadastro_cliente    = document.getElementById("cadastro_cliente");
cadastro_cliente.addEventListener("click", function() {
  window.location.href = "formCliente.html";
});

const cadastro_vendedor = document.getElementById("cadastro_vendedor");
cadastro_vendedor.addEventListener("click", function() {
  window.location.href = "formVendedor.html";
});

const teste = document.getElementById("teste");
teste.addEventListener("click", function() {
  window.location.href = "homeAdministrador.html";
});