
			
const tabela = "AdministradorDAO"; // troque pelo nome da tabela que deseja buscar

function buscaAdministrador() {
  fetch(`jdbc:postgresql://localhost:8080/domrock/${administrador}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  }) 	
    .then(function (res) {
      return res.json();
    })
    .then(function (data) {
      console.log(data); // exibe os dados da tabela no console
    })
    .catch(function (err) {
      console.log(err);
    })    
    .then(data => {
					if (data.authenticated){
						}else{
							alert("Nome de usuário ou senha incorretas.");
						}
				})
				.catch(error =>{
					console.error('Erro:Tudo deu errado', error);
				});
}
			