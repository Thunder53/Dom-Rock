const registrarBtn = document.getElementById("registrar");
const clienteSelect = document.getElementById("caixa-de-selecao-cliente");
const produtoSelect = document.getElementById("caixa-de-selecao-produto");
const dataInput = document.getElementById("data");
const quantidadeInput = document.getElementById("quantidade");

registrarBtn.addEventListener("click", cadastrar);


async function cadastrar() {
    const clienteNome = clienteSelect.value;
    const produtoNome = produtoSelect.value;
    const data = dataInput.value;
    const quantidade = quantidadeInput.value;
  
    try {
      const clienteResponse = await fetch(`http://localhost:8080/cliente/id-por-nome?nome_cliente=${clienteNome}`);
      const cliente = await clienteResponse.json();
  
      const produtoResponse = await fetch(`http://localhost:8080/produto/id-por-nome?nome_produto=${produtoNome}`);
      const produto = await produtoResponse.json();

      const id_usuario = localStorage.getItem('id');
  
      const venda = {
        fk_usuario_id: id_usuario,
        fk_cliente_cod_cliente: cliente,
        fk_produto_cod_produto: produto,
        criada_em : data,
        quant_estimada: quantidade
      };
  
      const response = await fetch("http://localhost:8080/venda", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(venda)
      });
  
      if (response.ok) {
        alert("Venda registrada com sucesso!");
      } else {
        alert("Erro ao registrar venda. Por favor, tente novamente.");
      }
    } catch (error) {
      alert("Erro ao acessar a API. Por favor, tente novamente mais tarde.");
      console.error(error);
    }
  }

  fetch(`http://localhost:8080/clientes-by-vendedor/${localStorage.getItem('id')}`)
  .then(response => response.json())
  .then(data => {
      data.forEach(cliente => {
          const option = document.createElement('option');
          option.text = cliente.nome_cliente;
          clienteSelect.appendChild(option);
      });
  })
  .catch(error => console.error(error));


    fetch("http://localhost:8080/produto/produto")
    .then(response => response.json())
    .then(data => {
        data.forEach(produto => {
            const option = document.createElement('option');
            option.text = produto.nome_produto;
            produtoSelect.appendChild(option);
        });
    })
    .catch(error => console.error(error));
