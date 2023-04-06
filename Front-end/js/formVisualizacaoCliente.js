function visualizar()
    fetch("http://localhost:8080/vendedor",        
     (document).ready(function() {
        ajax({
          url: 'http://localhost:8080/cliente', // URL do backend que fornece as informações do cliente
          type: 'GET', // Método HTTP usado para fazer a solicitação
          dataType: 'json', // Tipo de dados esperados em resposta
          success: function(data) {
            // Atualize os elementos HTML com os dados recebidos
            $('.info_clientes td:eq(0)').text(data.cod_cliente);
            $('.info_clientes td:eq(1)').text(data.nome_cliente);
            $('.info_clientes td:eq(2)').text(data.nome_gerencia);
          },
          error: function() {
            alert('Erro ao buscar informações do cliente');
          }
        });
      })
    );
