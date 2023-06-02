function isNumeric(value) {
  return !isNaN(parseFloat(value)) && isFinite(value);
}

function atualizarVenda(url) {
  const novoValor = prompt("Digite o novo valor da quantidade estimada:");

  if (novoValor === null) {
    return;
  }

  if (!isNumeric(novoValor)) {
    alert("Digite um valor numérico válido para a quantidade estimada.");
    return;
  }

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
        throw new Error("Erro ao atualizar a venda.");
      }
    })
    .then((data) => {
      console.log("Venda atualizada com sucesso:", data);
      alert("Venda atualizada com sucesso!");
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
        row.insertCell().textContent = cliente.nome_cliente;
        row.insertCell().textContent = cliente.nome_produto;
        row.insertCell().textContent = cliente.criada_em;

        const quantidadeEstimadaCell = row.insertCell();

        const valorSpan = document.createElement("span");
        valorSpan.textContent = cliente.quant_estimada;

        const editarButton = document.createElement("button");
        editarButton.textContent = "Editar";
        editarButton.classList.add("editar-button");

        quantidadeEstimadaCell.appendChild(valorSpan);
        quantidadeEstimadaCell.appendChild(editarButton);
        quantidadeEstimadaCell.classList.add("quantidade-estimada-cell");

        row.cells[0].classList.add("nome_cliente");
        row.cells[1].classList.add("nome_produto");
        row.cells[2].classList.add("criada_em");
        row.cells[3].classList.add("quant_estimada");

        editarButton.addEventListener("click", () => {
          const vendaId = cliente.id_venda;
          const url = `http://localhost:8080/venda/${vendaId}`;
          atualizarVenda(url);
        });
      });

      // Exibe a boxview
    })
    .catch((error) => console.error(error));
}

document.addEventListener("DOMContentLoaded", buscar);
