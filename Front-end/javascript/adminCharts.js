const selectProduto = document.getElementById('filtro-produto');
const selectVendedor = document.getElementById('filtro-vendedor');
const selectData = document.getElementById('filtro-data');

const BotaoVenda = document.getElementById('botao-venda');
const BotaoVendedor = document.getElementById('botao-vendedor');
const BotaoProduto = document.getElementById('botao-produto');

const filtroProduto = document.getElementById("filtro-button-produto");
const filtroVendedor = document.getElementById("filtro-button-vendedor");
const filtroData = document.getElementById("filtro-button-data");

filtroProduto.addEventListener("click", filtro_produto);
filtroVendedor.addEventListener("click", filtro_vendedor);
filtroData.addEventListener("click", filtro_data);


let vendaChart = null;

fetch("http://localhost:8080/produto/produto")
  .then(response => response.json())
  .then(data => {
    data.forEach(produto => {
      const option = document.createElement('option');
      option.text = produto.nome_produto;
      selectProduto.appendChild(option);
    });
  })
  .catch(error => console.error(error));

fetch("http://localhost:8080/usuario/vendedores")
  .then(response => response.json())
  .then(data => {
    data.forEach(usuario => {
      const option = document.createElement('option');
      option.text = usuario.nome;
      selectVendedor.appendChild(option);
    });
  })
  .catch(error => console.error(error));

function metasVendas() {
  fetch("http://localhost:8080/venda")
    .then(response => response.json())
    .then(data => {

      data.sort((a, b) => new Date(a.criada_em) - new Date(b.criada_em));

      const labels = data.map(item => {
        const date = new Date(item.criada_em);
        const formattedDate = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
        return formattedDate;
      });

      const totalEstimado = data.map(item => item.quant_estimada);
      const totalVendido = data.map(item => item.quant_vendida);

      const config = {
        type: "bar",
        data: {
          labels: labels,
          datasets: [{
            label: "Metas",
            data: totalEstimado,
            backgroundColor: "#005eff",
            borderColor: "black",
            borderWidth: 1,
          },
          {
            label: "Vendas",
            data: totalVendido,
            backgroundColor: "#a52a2a",
            borderColor: "black",
            borderWidth: 1,
          }],
        },
        options: {
          scales: {
            x: {
              title: {
                display: true,
                text: "Data estimada",
              },
            },
            y: {
              title: {
                display: true,
                text: "Total Vendido",
              },
              beginAtZero: true,
            },
          },
        },
      };

      const ctx = document.getElementById("bar-chart").getContext("2d");
      if (vendaChart) {
        vendaChart.destroy();
      }
      vendaChart = new Chart(ctx, config)
    })
    .catch(error => console.log(error));
}

function rankingVendedor() {
  fetch("http://localhost:8080/venda/topVendedores")
    .then(response => response.json())
    .then(data => {

      const labels = data.map(item => item.nome_vendedor);
      const totalEstimado = data.map(item => item.total_vendido);
      const config = {
        
        type: "bar",
        data: {
          labels: labels,
          datasets: [{
            label: "Total",
            data: totalEstimado,
            backgroundColor: "#005eff",
            borderColor: "black",
            borderWidth: 1,
          }],
        },
        options: {
          scales: {
            x: {
              title: {
                display: true,
                text: "Vendedor",
              },
            },
            y: {
              title: {
                display: true,
                text: "Total de venda",
              },
              beginAtZero: true,
            },
          },
        },
      };

      const ctx = document.getElementById("bar-chart").getContext("2d");
      if (vendaChart) {
        vendaChart.destroy();
      }
      vendaChart = new Chart(ctx, config)
    })
    .catch(error => console.log(error));
}

function rankingProdutos() {
  fetch("http://localhost:8080/produto/topProdutos")
    .then(response => response.json())
    .then(data => {

      const labels = data.map(item => item.nome_produto);
      const totalEstimado = data.map(item => item.total_vendido);
      const config = {
        
        type: "bar",
        data: {
          labels: labels,
          datasets: [{
            label: "Total",
            data: totalEstimado,
            backgroundColor: "#005eff",
            borderColor: "black",
            borderWidth: 1,
          }],
        },
        options: {
          scales: {
            x: {
              title: {
                display: true,
                text: "Produtos",
              },
            },
            y: {
              title: {
                display: true,
                text: "Total de venda",
              },
              beginAtZero: true,
            },
          },
        },
      };

      const ctx = document.getElementById("bar-chart").getContext("2d");
      if (vendaChart) {
        vendaChart.destroy();
      }
      vendaChart = new Chart(ctx, config)
    })
    .catch(error => console.log(error));
}

BotaoVenda.addEventListener('click', () => {
  if (vendaChart) {
    vendaChart.destroy();
  }
  metasVendas();
});

BotaoVendedor.addEventListener('click', () => {
  if (vendaChart) {
    vendaChart.destroy();
  }
  rankingVendedor();
});

BotaoProduto.addEventListener('click', () => {
  if (vendaChart) {
    vendaChart.destroy();
  }
  rankingProdutos();
});

async function filtro_produto() {
  const produtoNome = selectProduto.value;

  if (selectProduto.value === "Todos") {
    if (vendaChart) {
      vendaChart.destroy();
    }
    metasVendas();
  }

  const produtoResponse = await fetch(`http://localhost:8080/produto/id-por-nome?nome_produto=${produtoNome}`);
  const produto = await produtoResponse.json();

  fetch(`http://localhost:8080/venda/filtro-produto/${produto}`)
    .then(response => response.json())
    .then(data => {

      data.sort((a, b) => new Date(a.criada_em) - new Date(b.criada_em));

      const labels = data.map(item => {
        const date = new Date(item.criada_em);
        const formattedDate = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
        return formattedDate;
      });

      const totalEstimado = data.map(item => item.quant_estimada);
      const totalVendido = data.map(item => item.quant_vendida);

      const config = {
        type: "bar",
        data: {
          labels: labels,
          datasets: [
            {
              label: "Total Estimado",
              data: totalEstimado,
              backgroundColor: "#005eff",
              borderColor: "black",
              borderWidth: 1,
            },
            {
              label: "Total Vendido",
              data: totalVendido,
              backgroundColor: "#a52a2a",
              borderColor: "black",
              borderWidth: 1,
            },
          ],
        },
        options: {
          responsive: true,
          scales: {
            x: {
              grid: {
                display: false,
              },
            },
            y: {
              grid: {
                display: true,
              },
              beginAtZero: true,
            },
          },
        },
      };

      const barChartCanvas = document.getElementById("bar-chart");
      if (vendaChart) {
        vendaChart.destroy(); 
      }
      vendaChart = new Chart(barChartCanvas, config);
    })
    .catch(error => console.log(error));
}

async function filtro_vendedor() {
  
  const vendedorNome = selectVendedor.value;

  if (selectVendedor.value === "Todos") {
    if (vendaChart) {
      vendaChart.destroy();
    }
    metasVendas();
  }

  const vendedorResponse = await fetch(`http://localhost:8080/usuario/usuario-por-nome?nome=${vendedorNome}`);
  const vendedor = await vendedorResponse.json();

  fetch(`http://localhost:8080/venda/filtro-vendedor/${vendedor}`)
    .then(response => response.json())
    .then(data => {

      data.sort((a, b) => new Date(a.criada_em) - new Date(b.criada_em));
      
      const labels = data.map(item => {
        const date = new Date(item.criada_em);
        const formattedDate = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
        return formattedDate;
      });

      const totalEstimado = data.map(item => item.quant_estimada);
      const totalVendido = data.map(item => item.quant_vendida);

      const config = {
        type: "bar",
        data: {
          labels: labels,
          datasets: [
            {
              label: "Total Estimado",
              data: totalEstimado,
              backgroundColor: "#005eff",
              borderColor: "black",
              borderWidth: 1,
            },
            {
              label: "Total Vendido",
              data: totalVendido,
              backgroundColor: "#a52a2a",
              borderColor: "black",
              borderWidth: 1,
            },
          ],
        },
        options: {
          responsive: true,
          scales: {
            x: {
              grid: {
                display: false,
              },
            },
            y: {
              grid: {
                display: true,
              },
              beginAtZero: true,
            },
          },
        },
      };

      const barChartCanvas = document.getElementById("bar-chart");
      if (vendaChart) {
        vendaChart.destroy(); // Destruir o gráfico existente, se houver
      }
      vendaChart = new Chart(barChartCanvas, config);
    })
    .catch(error => console.log(error));
}

async function filtro_data() {
  const data = selectData.value;

  if (selectData.value === "Todos") {
    if (vendaChart) {
      vendaChart.destroy();
    }
    metasVendas();
  }

  fetch(`http://localhost:8080/venda/filtro-data/${data}`)
    .then(response => response.json())
    .then(data => {
      const labels = data.map(item => {
        const date = new Date(item.criada_em);
        const formattedDate = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
        return formattedDate;
      });

      const totalEstimado = data.map(item => item.quant_estimada);
      const totalVendido = data.map(item => item.quant_vendida);

      const config = {
        type: "bar",
        data: {
          labels: labels,
          datasets: [
            {
              label: "Total Estimado",
              data: totalEstimado,
              backgroundColor: "#005eff",
              borderColor: "black",
              borderWidth: 1,
            },
            {
              label: "Total Vendido",
              data: totalVendido,
              backgroundColor: "#a52a2a",
              borderColor: "black",
              borderWidth: 1,
            },
          ],
        },
        options: {
          responsive: true,
          scales: {
            x: {
              grid: {
                display: false,
              },
            },
            y: {
              grid: {
                display: true,
              },
              beginAtZero: true,
            },
          },
        },
      };

      const barChartCanvas = document.getElementById("bar-chart");
      if (vendaChart) {
        vendaChart.destroy(); 
      }
      vendaChart = new Chart(barChartCanvas, config);
    })
    .catch(error => console.log(error));
}

metasVendas();