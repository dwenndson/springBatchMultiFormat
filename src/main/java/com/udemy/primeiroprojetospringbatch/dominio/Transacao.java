package com.udemy.primeiroprojetospringbatch.dominio;

public class Transacao {

    private String transacao;
    private String descricao;
    private Double valor;

    public String getTransacao() {
        return transacao;
    }

    public void setTransacao(String transacao) {
        this.transacao = transacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }


    @Override
    public String toString() {
        return "Transacao{" + "id='" + transacao + "'" + ", descricao='" + descricao + "'" + ", valor='" + valor + "'" + '}';
    }
}
