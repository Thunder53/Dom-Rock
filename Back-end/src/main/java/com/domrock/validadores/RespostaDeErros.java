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

