const formulario = document.querySelector("form");
const Inome_cliente = document.querySelector(".nome_cliente");
const Inome_gerencia = document.querySelector(".nome_gerencia");
const Iproduto_a = document.querySelector(".produto_a");
const Iproduto_b = document.querySelector(".produto_b");
const Iproduto_c = document.querySelector(".produto_c");

function cadastrar(){
    fetch("http://localhost:8080/cliente",
    {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            nome_cliente: Inome_cliente.value,
            nome_gerencia: Inome_gerencia.value,
            produto_a: Iproduto_a.value,
            produto_b: Iproduto_b.value,
            produto_c: Iproduto_c.value
        })
    })
    .then(function(res) {console.log(res)})
    .catch(function(res) {console.log(res)})
};

function limpar(){
    Inome_cliente.value = "";
    Inome_gerencia.value = "";
    Iproduto_a.value = "";
    Iproduto_b.value = "";
    Iproduto_c.value = "";
}

formulario.addEventListener('submit', function(event){
    event.preventDefault();

    if (Inome_cliente.value === "" || Inome_cliente.value === "" || Iproduto_a.value === "" || Iproduto_b.value === "" || Iproduto_c.value === "") {
        alert("Insira todos os campos.");
    } else {
        cadastrar();
        limpar();
        alert("Cliente cadastrado com sucesso.");
    }
});