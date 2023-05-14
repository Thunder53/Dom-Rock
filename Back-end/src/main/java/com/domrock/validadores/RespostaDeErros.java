package com.domrock.validadores;

public class RespostaDeErros {
    private String message;
    private int status;

    public RespostaDeErros(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

}

// public RespostaDeErros validarCampos(UsuarioRequestDTO data) {
// if (!validarNome()) {
// return new ErrorResponse("Nome inválido", HttpStatus.BAD_REQUEST.value());
// }
// if (!validarEmail()) {
// return new ErrorResponse("Email inválido", HttpStatus.BAD_REQUEST.value());
// }
// if (!validarSenha()) {
// return new ErrorResponse("Senha inválida", HttpStatus.BAD_REQUEST.value());
// }
// if (!validarCpf()) {
// return new ErrorResponse("CPF inválido", HttpStatus.BAD_REQUEST.value());
// }
// if (!validarContato()) {
// return new ErrorResponse("Contato inválido", HttpStatus.BAD_REQUEST.value());
// }
// return null;
// }
