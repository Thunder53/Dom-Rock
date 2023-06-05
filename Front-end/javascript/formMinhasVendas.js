const formulario = document.querySelector("form");
const cadastro_vendida = document.getElementById("botao_cadastrar");
const edicao_vendida = document.getElementById("botao_editar");
let selectedRow = null;

cadastro_vendida.addEventListener("click", cadastrar_vendas);
edicao_vendida.addEventListener("click", editar_vendas);

function buscar() {
  const url = `http://localhost:8080/produto/produto-com-cliente/${localStorage.getItem('id')}`;

  fetch(url)
    .then(response => response.json())
    .then(data => {
      const infoCliente = document.querySelector(".info_cliente");
      infoCliente.innerHTML = '';

      data.forEach(cliente => {
        const row = infoCliente.insertRow();
        row.insertCell().textContent = cliente.id_venda;
        row.insertCell().textContent = cliente.nome_cliente;
        row.insertCell().textContent = cliente.nome_produto;
        row.insertCell().textContent = cliente.criada_em;
        row.insertCell().textContent = cliente.quant_estimada;
        row.insertCell().textContent = cliente.quant_vendida;

        row.cells[0].classList.add("id_venda");
        row.cells[1].classList.add("cliente-nome");
        row.cells[2].classList.add("produto-nome");
        row.cells[3].classList.add("data-criacao");
        row.cells[4].classList.add("quant-estimada");
        row.cells[5].classList.add("quant-vendida");

        row.addEventListener("click", () => {
          if (selectedRow) {
            selectedRow.classList.remove('selected');
          }
          row.classList.add('selected');
          selectedRow = row;
        });
      });
    })
    .catch(error => console.error(error));
}

document.addEventListener('DOMContentLoaded', buscar);

function cadastrar_vendas() {
  if (selectedRow) {
    const selectedId = selectedRow.getAttribute('data-id');
    console.log('Cadastro de Vendas - Linha selecionada. ID:', selectedId);
    const id_venda = selectedRow.querySelector(".id_venda").textContent;

    const quantidadeVendida = prompt('Informe a quantidade vendida:');

    const url = `http://localhost:8080/venda/cadastrar_quantidade/${id_venda}/${quantidadeVendida}`;

    fetch(url, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then(response => response.json())
      .then(data => {
        console.log(data);
        buscar();
      })
      .catch(error => {
        console.error('Erro na requisição:', error);
      });
    
    selectedRow.classList.remove('selected');
    selectedRow = null;
  } else {
    alert('Clique na linha que você deseja alterar.');
  }
}

function editar_vendas() {
  if (selectedRow) {
    const selectedId = selectedRow.getAttribute('data-id');
    console.log('Cadastro de Vendas - Linha selecionada. ID:', selectedId);
    const id_venda = selectedRow.querySelector(".id_venda").textContent;

    const quantidadeVendida = prompt('Informe a quantidade vendida:');

    const url = `http://localhost:8080/venda/atualizar_venda/${id_venda}/${quantidadeVendida}`;

    fetch(url, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then(response => response.json())
      .then(data => {
        console.log(data);
        buscar(); // Chama a função buscar para atualizar os dados na página
      })
      .catch(error => {
        console.error('Erro na requisição:', error);
      });
    
    selectedRow.classList.remove('selected');
    selectedRow = null;
  } else {
    alert('Clique na linha que você deseja alterar.');
  }
}
