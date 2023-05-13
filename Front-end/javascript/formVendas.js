const registrar = document.getElementById("registrar");
const Icliente = document.getElementById("cliente");
const Iproduto = document.getElementById("produto");
const Idata = document.getElementById("data");
const Iquantidade = document.getElementById("quantidade");
const select = document.getElementById('caixa-de-selecao-cliente');

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