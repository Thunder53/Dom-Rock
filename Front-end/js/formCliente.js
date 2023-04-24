const formulario = document.querySelector("form");
const Inome_cliente = document.querySelector(".nome_cliente");
const Inome_gerencia = document.querySelector(".nome_gerencia");
const select = document.getElementById('caixa-de-selecao');
let list = [];


function cadastrar() {
    const nomeVendedor = select.value.toString();
    fetch(`http://localhost:8080/usuario/usuario-por-nome?nome=${nomeVendedor}`)
      .then(response => response.json())
      .then(data => {
        const idVendedor = parseInt(data);
        console.log(idVendedor);
        list.push(idVendedor); 
        const primeiroValor = list[0];
        const id = parseInt(primeiroValor);
        console.log(id);
        fetch("http://localhost:8080/cliente", {
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          method: "POST",
          body: JSON.stringify({
            nome_cliente: Inome_cliente.value,
            nome_gerencia: Inome_gerencia.value,
            fk_usuario_id: id
          })
        })
      })
      .catch(error => {
        console.error(error);
        alert("Erro ao buscar vendedor.");
      });
  };


function limpar(){
    Inome_cliente.value = "";
    Inome_gerencia.value = "";
}

formulario.addEventListener('submit', function(event){
    event.preventDefault();

    if (Inome_cliente.value === "" || Inome_cliente.value === "" ) {
        alert("Insira todos os campos.");
    } else {
        cadastrar();
        alert("Cliente cadastrado com sucesso.");
    }
});


fetch("http://localhost:8080/usuario")
    .then(response => response.json())
    .then(data => {
        data.forEach(usuario => {
            const option = document.createElement('option');
            option.text = usuario.nome;
            select.appendChild(option);
        });
    })
    .catch(error => console.error(error));
