fetch(`http://localhost:8080/clientes-by-vendedor/${localStorage.getItem('id')}`)
    .then(response => response.json())
    .then(data => {
        const boxview = document.querySelector(".boxview");
        const infoCliente = document.querySelector(".info_cliente");

      // Adiciona cada cliente como uma nova linha na tabela
      data.forEach(cliente => {
        const row = infoCliente.insertRow();
        row.insertCell().textContent = cliente.cod_cliente;
        row.insertCell().textContent = cliente.nome_cliente;

        row.cells[0].classList.add("cod_cliente");
        row.cells[1].classList.add("nome_cliente");
       });

      // Exibe a boxview
    })
    .catch(error => console.error(error));