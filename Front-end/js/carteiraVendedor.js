function buscar(){
    const urlCliente = "http://localhost:8080/cliente";

    fetch(urlCliente)
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
        });
       
      })
      .catch(error => console.error(error));
};

// Executa a função buscar assim que o documento HTML é completamente carregado
document.addEventListener('DOMContentLoaded', buscar);


const botao = document.getElementById("botaoAtualizar");
botao.addEventListener("click", function() {
    buscar();
});