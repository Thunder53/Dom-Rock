const formulario = document.querySelector("form");
const Inome = document.querySelector(".input_login");
const Isenha = document.querySelector(".input_senha");
const Ibotao = document.querySelector("#botao")

function homeAdministrador() {
    var novaPagina = "formVendedor.html";
    window.open(novaPagina);
    window.close();
  }

if (Ibotao) {
    Ibotao.addEventListener('click', function(event) {
        const email = Inome.value;
        const password = Isenha.value;
      
        // Verifica se o email e a senha correspondem aos valores esperados
        if (email === 'ariane@domrock.com' && password === '123456789') {
            homeAdministrador();
        } else {
            alert('Login ou senha incorretos!');
        }
    });
  } else {
    console.error('Botão não encontrado no DOM!');
  }



