package br.edu.unifeob.grafico.dao;

import br.edu.unifeob.grafico.entidades.Produto;
import br.edu.unifeob.grafico.entidades.Venda;
import br.edu.unifeob.grafico.util.UtilData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by osvaldogusmao on 09/06/14.
 */
public class VendasDAO {


    public static List<Venda> criaListaVendas() {

        List<Venda> vendas = new ArrayList<Venda>();
        float total = 0f;

        for (int i = 0; i <= 100; i++) {
            Venda venda = new Venda();
            Calendar data = Calendar.getInstance();
            data.add(Calendar.DATE, i);
            venda.setData(data);
            venda.setQuantidade(Float.valueOf(i));

            for (int j = 0; j <= 1; j++) {
                Produto produto = new Produto();
                produto.setId(Long.valueOf(i + j));
                produto.setDescricao("Produto " + j);
                produto.setPrecoUnitario(20f + i + j);
                total += produto.getPrecoUnitario();
                venda.addProduto(produto);
            }

            venda.setValor(total);
            vendas.add(venda);
        }

        return vendas;
    }

    public static List<Venda> filtraPorData(String dataInicialTexto, String dataFinalTexto, List<Venda> vendasTotal) {

        Calendar dataInicial = Calendar.getInstance();
        Calendar dataFinal = Calendar.getInstance();
        List<Venda> vendasFiltradas = new ArrayList<Venda>();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataInicial.setTime(format.parse(dataInicialTexto));
            dataFinal.setTime(format.parse(dataFinalTexto));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Venda v : vendasTotal) {
            if ((v.getData().after(dataInicial) || v.getData().compareTo(dataInicial) == 0) && (v.getData().before(dataFinal) || v.getData().compareTo(dataFinal) == 0)) {
                vendasFiltradas.add(v);
            }
        }

        return vendasFiltradas;
    }


    public static String preparaDadosParaGrafico(List<Venda> vendas){

        String grafico = "['Data', 'Valor']";

            for (Venda venda : vendas){
                grafico += ",['"+ UtilData.converterEmPadraoBR(venda.getData())+"',"+venda.getValor()+"]";
            }
        System.out.println(grafico);
        return grafico;
    }

}
