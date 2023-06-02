function buscar() {
    const url = (`http://localhost:8080/produto/produto-com-cliente/${localStorage.getItem('id')}`)
  
    fetch(url)
      .then(response => response.json())
      .then(data => {
        const boxview = document.querySelector(".boxview");
        const infoCliente = document.querySelector(".info_cliente");
  
    
        // Adiciona cada cliente como uma nova linha na tabela
        data.forEach(cliente => {
          const row = infoCliente.insertRow();
          row.insertCell().textContent = cliente.nome_cliente;
          row.insertCell().textContent = cliente.nome_produto;
          row.insertCell().textContent = cliente.criada_em;
          row.insertCell().textContent = cliente.quant_estimada;
          row.insertCell().textContent = cliente.quant_vendida;
  
          row.cells[0].classList.add("nome_cliente");
          row.cells[1].classList.add("nome_produto");
          row.cells[2].classList.add("criada_em");
          row.cells[3].classList.add("quant_estimada");
          row.cells[4].classList.add("quant_vendida");
         });
  
        // Exibe a boxview
      })
      .catch(error => console.error(error));
  };
  
  // Executa a função buscar
  
  
  // Executa a função buscar assim que o documento HTML é completamente carregado
  document.addEventListener('DOMContentLoaded', buscar);
  
  
 