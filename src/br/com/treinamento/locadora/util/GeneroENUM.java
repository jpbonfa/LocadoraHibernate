package br.com.treinamento.locadora.util;

public enum GeneroENUM {

    ACAO(1, "Ação"), FICCAO(2, "Ficção"), TERROR(3, "Terror"), COMEDIA(
            4, "Comédia"), INFANTIL(5, "Infantil"), ANIMACAO(6, "Animação"), AVENTURA(7, "Aventura"), OUTROS(8, "Outros");

    private int codigo;
    private String descricao;

    GeneroENUM(int codigo, String periodo) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

}
