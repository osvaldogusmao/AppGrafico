package br.edu.unifeob.grafico.entidades;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by osvaldogusmao on 29/04/14.
 */
public class Venda {

    private Calendar data;
    private Float quantidade;
    private List<Produto> produtos = new ArrayList<Produto>();
    private Float valor;

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {

        String retorno = "";

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        return "Data : " + format.format(data.getTime()) + " | Total : " + NumberFormat.getCurrencyInstance().format(valor) + "\n\n" +
                "Produtos : " + produtos.get(0).getDescricao() + ", " + produtos.get(1).getDescricao();

    }


}
