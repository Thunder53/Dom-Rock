const registrar = document.getElementById("registrar");
const Icliente = document.getElementById("cliente");
const Iproduto = document.getElementById("produto");
const Idata = document.getElementById("data");
const Iquantidade = document.getElementById("quantidade");
const select = document.getElementById('caixa-de-selecao-cliente');
const selectProduto = document.getElementById('caixa-de-selecao-produto');
let listCliente = [];
let listProduto = [];

function cadastrar() {
    const nomeCliente = select.value.toString();
    const nomeProduto = selectProduto.value.toString();
  
    fetch(`http://localhost:8080/cliente/id-por-nome?nome_cliente=${nomeCliente}`)
      .then(response => response.json())
      .then(data => {
        const cod_cliente = parseInt(data);
        listCliente.push(cod_cliente);
        const primeiroValor = listCliente[0];
        return parseInt(primeiroValor);
      })
      .then(idCliente => {
        return fetch(`http://localhost:8080/id-por-nome?nome_produto=${nomeProduto}`)
          .then(response => response.json())
          .then(data => {
            const cod_produto = parseInt(data);
            listProduto.push(cod_produto);
            const primeiroValor = listProduto[0];
            const idProduto = parseInt(primeiroValor);
  
            return fetch("http://localhost:8080/venda", {
              headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
              },
              method: "POST",
              body: JSON.stringify({
                quant_vendida: null,
                atualizada_em: null,
                quant_estimada: Iquantidade.value,
                criada_em: Idata.value,
                fk_usuario_id: localStorage.getItem('id'),
                fk_cliente_cod_cliente: idCliente,
                fk_produto_cod_produto: idProduto
              })
            });
          });
      })
      .then(response => {
        if (!response.ok) {
          throw new Error("Erro ao cadastrar a venda.");
        }
        alert("Venda cadastrada com sucesso!");
      })
      .catch(error => {
        console.error(error);
        alert("Ocorreu um erro ao cadastrar a venda.");
      });
  };
  


function limpar(){
    Inome.value = "";
    Iemail.value = "";
    Isenha.value = "";
    Icpf.value = "";
    Icontato.value = "";
}

registrar.addEventListener('click', function(){
    cadastrar();
});

fetch(`http://localhost:8080/clientes-by-vendedor/${localStorage.getItem('id')}`)
    .then(response => response.json())
    .then(data => {
        data.forEach(cliente => {
            const option = document.createElement('option');
            option.text = cliente.nome_cliente;
            select.appendChild(option);
        });
    })
    .catch(error => console.error(error));

fetch(`http://localhost:8080/produto`)
    .then(response => response.json())
    .then(data => {
        data.forEach(produto => {
            const option = document.createElement('option');
            option.text = produto.nome_produto;
            selectProduto.appendChild(option);
        });
    })
    .catch(error => console.error(error));