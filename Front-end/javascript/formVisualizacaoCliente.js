function buscar() {
  const url = "http://localhost:8080/clientes-com-vendas";

  fetch(url)
    .then(response => response.json())
    .then(data => {
      const boxview = document.querySelector(".boxview");
      const infoCliente = document.querySelector(".info_cliente");

      while (infoCliente.rows.length > 1) {
        infoCliente.deleteRow(1);
      }

      data.forEach(cliente => {
        const row = infoCliente.insertRow();
        row.insertCell().textContent = cliente.Nome_Cliente;
        row.insertCell().textContent = cliente.Nome_Gerencia;
        row.insertCell().textContent = cliente.nome;

        row.cells[0].classList.add("Nome_Cliente");
        row.cells[1].classList.add("Nome_Gerencia");
        row.cells[2].classList.add("nome");
       });

    })
    .catch(error => console.error(error));
};

document.addEventListener('DOMContentLoaded', buscar);

const botao = document.getElementById("botao");
botao.addEventListener("click", function() {
  window.location.href = "formCliente.html";
});