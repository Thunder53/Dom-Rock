
function buscar(){
  const url = "http://localhost:8080/cliente";

  fetch(url)
    .then(response => response.json())
    .then(data => {
      const boxview = document.querySelector(".boxview");
      const infoCliente = document.querySelector(".info_cliente");

      // Remove linhas anteriores da tabela, mantendo apenas a primeira linha de cabeçalho
      while (infoCliente.rows.length > 1) {
        infoCliente.deleteRow(1);
      }

      // Adiciona cada cliente como uma nova linha na tabela
      data.forEach(clienteList => {
        const row = infoCliente.insertRow();
        row.insertCell().textContent = clienteList.cod_cliente;
        row.insertCell().textContent = clienteList.nome_cliente;
        row.insertCell().textContent = clienteList.nome_gerencia;
        row.insertCell().textContent = clienteList.quant_vendida;
        row.insertCell().textContent = clienteList.fk_cliente_cod_cliente;
        
      });

      row.cells[0].classList.add("cod_cliente");
      row.cells[1].classList.add("nome_cliente");
      row.cells[2].classList.add("nome_gerencia");
      row.cells[3].classList.add("quant_vendida");
      row.cells[4].classList.add("fk_cliente_cod_cliente");
      
      

      // Exibe a boxview
     
    })

    .catch(error => console.error(error_cliente));

  
};

// Executa a função buscar assim que o documento HTML é completamente carregado
document.addEventListener('DOMContentLoaded', buscar);


const botao = document.getElementById("botao");
botao.addEventListener("click", function() {
  window.location.href = "formCarteiraCliente.html";
});