function checarCredencial(){
			var img_usuario = document.getElementById("img_usuario").value;
			var img_senha = document.getElementById("img_senha").value;
			
			fetch('https://example.com/api/verificaCredecial',{
				method: 'POST',
				body: JSON.stringify({img_usuario:img_usuario, img_senha:img_senha}),
				headers:{'Content-Type':'application/json'}
				})
				.then(response => response.json())
				.then(data => {
					if (data.authenticated){
						window.location.href = "pagina_vendedor.html";
						}else{
							alert("Nome de usuário ou senha incorretas.");
						}
				})
				.catch(error =>{
					console.error('Erro:Tudo deu errado', error);
				});
			}