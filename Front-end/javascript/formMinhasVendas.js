function isNumeric(value) {
  return !isNaN(parseFloat(value)) && isFinite(value);
}

function atualizarVenda(vendaId) {
  const novoValor = prompt("Digite o novo valor da quantidade estimada:");

  if (novoValor === null) {
    return;
  }

  if (!isNumeric(novoValor)) {
    alert("Digite um valor numérico válido para a quantidade estimada.");
    return;
  }

  console.log("Valor da vendaId:", vendaId);

  const url = `http://localhost:8080/venda/${vendaId}`;

  fetch(url, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      quant_estimada: parseFloat(novoValor),
    }),
  })
    .then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        return response.text().then((errorMessage) => {
          throw new Error(errorMessage);
        });
      }
    })
    .then((data) => {
      console.log("Venda atualizada com sucesso:", data);
      alert("Venda atualizada com sucesso!");
      location.reload();
    })
    .catch((error) => {
      console.error("Erro ao atualizar venda:", error);
      alert(error.message);
    });
}

function buscar() {
  const url = `http://localhost:8080/produto/produto-com-cliente/${localStorage.getItem(
    "id"
  )}`;

  fetch(url)
    .then((response) => response.json())
    .then((data) => {
      const boxview = document.querySelector(".boxview");
      const infoCliente = document.querySelector(".info_cliente");

      data.forEach((cliente) => {
        const row = infoCliente.insertRow();
        row.insertCell().textContent = cliente.id_venda; // Coluna do ID da venda

        console.log("id_venda: " + cliente.id_venda);

        row.insertCell().textContent = cliente.nome_cliente;

        console.log("nome_cliente: " + cliente.nome_cliente);

        row.insertCell().textContent = cliente.nome_produto;

        console.log("nome_produto: " + cliente.nome_produto);

        row.insertCell().textContent = cliente.criada_em;

        console.log("criada_em: " + cliente.criada_em);

        const quantidadeEstimadaCell = row.insertCell();
        const editarButton = document.createElement("button");
        editarButton.textContent = "Editar";
        editarButton.classList.add("editar-button");

        quantidadeEstimadaCell.appendChild(
          document.createTextNode(cliente.quant_estimada + " ")
        );
        quantidadeEstimadaCell.appendChild(editarButton);
        quantidadeEstimadaCell.classList.add("quantidade-estimada-cell");

        row.cells[0].classList.add("id_venda");
        row.cells[1].classList.add("nome_cliente");
        row.cells[2].classList.add("nome_produto");
        row.cells[3].classList.add("criada_em");
        row.cells[4].classList.add("quant_estimada");

        editarButton.addEventListener("click", function () {
          const rowIndex = Array.from(this.closest("tbody").children).indexOf(
            this.parentNode.parentNode
          );

          const vendaId = cliente.id_venda;
          console.log("Venda ID: " + vendaId);

          atualizarVenda(vendaId);
        });
      });

      // Exibe a boxview
    })
    .catch((error) => console.error(error));
}

document.addEventListener("DOMContentLoaded", buscar);
