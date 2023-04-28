const formulario = document.querySelector("form");
const Inome = document.querySelector(".nome");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");
const Icpf = document.querySelector(".cpf");
const Icontato = document.querySelector(".contato");

function cadastrar(){
    fetch("http://localhost:8080/usuario",
    {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            nome: Inome.value,
            email: Iemail.value,
            senha: Isenha.value,
            cpf: Icpf.value,
            contato: Icontato.value
        })
    })
    .then(function(res) {console.log(res)})
    .catch(function(res) {console.log(res)})
};

function limpar(){
    Inome.value = "";
    Iemail.value = "";
    Isenha.value = "";
    Icpf.value = "";
    Icontato.value = "";
}

formulario.addEventListener('submit', function(event){
    event.preventDefault();

    if (Inome.value === "" || Iemail.value === "" || Isenha.value === "" || Icpf.value === "" || Icontato.value === "") {
        alert("Insira todos os campos.");
    } else {
        cadastrar();
        limpar();
        alert("Vendedor cadastrado com sucesso.");
    }
});