package br.edu.unifeob.grafico.entidades;

/**
 * Created by osvaldogusmao on 03/06/14.
 */
public class Produto {

    private Long id;
    private String descricao;
    private Float precoUnitario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public String toString() {
        return this.descricao + "- R$ " + this.precoUnitario;
    }
}
