package br.edu.unifeob.grafico.infra;

import br.edu.unifeob.grafico.entidades.Venda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osvaldogusmao on 29/04/14.
 */
public class DadosInstance {

    private List<Venda> vendas = new ArrayList<Venda>();
    private static DadosInstance dadosInstance;


    private DadosInstance() {

    }

    public static DadosInstance getInstance() {

        if (dadosInstance == null) {
            dadosInstance = new DadosInstance();
        }

        return dadosInstance;
    }


    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public List<Venda> getVendas() {
        return vendas;
    }
}
