const formulario = document.querySelector("form");
const Inome_produto = document.getElementById("nome_produto");

function cadastrar() {
        fetch("http://localhost:8080/produto/produto", {
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          method: "POST",
          body: JSON.stringify({
            nome_produto: Inome_produto.value,
          })
        })
  };

function limpar(){
    Inome_cliente.value = "";
    Inome_gerencia.value = "";
}

formulario.addEventListener('submit', function(event){
    event.preventDefault();
    if (Inome_produto.value === "") {
        alert("Insira todos os campos.");
    } else {
        cadastrar();
        alert("Produto cadastrado com sucesso.");
    }
});
