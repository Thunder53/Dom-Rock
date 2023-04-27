const registrar = document.getElementById("registrar");
const Icliente = document.getElementById("cliente");
const Iproduto = document.getElementById("produto");
const Idata = document.getElementById("data");
const Iquantidade = document.getElementById("quantidade")

function cadastrar(){
    fetch("http://localhost:8080/venda",
    {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            nome_cliente: Icliente.value,
            produto: Iproduto.value,
            data_estimada: Idata.value,
            quant_estimada: Iquantidade.value,
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

registrar.addEventListener('click', function(){
    if (Icliente.value === "" || Iproduto.value === "" || Idata.value === "" || Iquantidade.value === "") {
        alert("Insira todos os campos.");
    } else {
        cadastrar();
        limpar();
        alert("Vendedor cadastrado com sucesso.");
    }
});