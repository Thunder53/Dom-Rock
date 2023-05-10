const formulario = document.querySelector("form");
const Ibotao = document.querySelector("#botao")
const Iemail = document.querySelector(".input_login");
const Isenha = document.querySelector(".input_senha");

function homeVendedor() {
  var novaPagina = "../view/homeVendedor.html";
  window.open(novaPagina);
  window.close();
}

function fazerLogin() {
  let email = document.getElementById("img_usuario").value;
  let senha = document.getElementById("img_senha").value;
  let usuario = {email: email, senha: senha};
  fetch('http://localhost:8080/login', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json'
      },
      body: JSON.stringify(usuario)
  })
  .then(response => {
      if (response.ok) {
          response.json().then(data => {
              let id = data;
              // Salvar id em uma variável para uso em outras páginas
              homeVendedor();
          });
      } else {
          alert('Email ou senha inválidos');
      }
  })
  .catch(error => {
      console.error(error);
  });
}

function homeAdministrador() {
    var novaPagina = "../view/homeAdministrador";
    window.open(novaPagina);
    window.close();
  }

formulario.addEventListener('submit', function(event){
    event.preventDefault();
    if ( Iemail.value.trim() === "" || Isenha.value.trim() === "") {
      alert("Insira valores aos campos")
    } else {
      if ( Iemail === "ariane@domrock.com" && Isenha === "123456789"){
        homeAdministrador();
      } else {
        fazerLogin();
      }
    }
});






