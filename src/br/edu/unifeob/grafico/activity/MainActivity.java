package br.edu.unifeob.grafico.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import br.edu.unifeob.grafico.dao.VendasDAO;
import br.edu.unifeob.grafico.entidades.Venda;
import br.edu.unifeob.grafico.infra.DadosInstance;
import com.example.grafico.R;
import br.edu.unifeob.grafico.util.UtilData;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity {

    /**
     * Objetos
     */

    Button btnSelecionaDataInicial;
    Button btnSelecionaDataFinal;
    ListView lsVendas;

    /**
     * Atributos
     */
    int dia;
    int mes;
    int ano;
    String dataInicialSelecionada;
    String dataFinalSelecionada;

    List<Venda> vendas;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        btnSelecionaDataInicial = (Button) findViewById(R.id.btnSelecionarDataInicial);
        btnSelecionaDataFinal = (Button) findViewById(R.id.btnSelecionarDataFinal);
        lsVendas = (ListView) findViewById(R.id.lsVendas);


        Calendar data = Calendar.getInstance();
        dia = data.get(Calendar.DATE);
        mes = data.get(Calendar.MONTH) + 1;
        ano = data.get(Calendar.YEAR);

        dataInicialSelecionada = dia + "/" + mes + "/" + ano;
        dataFinalSelecionada = dia + "/" + mes + "/" + ano;
        btnSelecionaDataInicial.setText(dataInicialSelecionada);
        btnSelecionaDataFinal.setText(dataFinalSelecionada);


        this.vendas = VendasDAO.criaListaVendas();
        populaListaView(vendas);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void selecionarDataInicial(View view) {
        showDialog(view.getId());
    }

    public void selecionarDataFinal(View view) {
        showDialog(view.getId());
    }

    @Override
    protected Dialog onCreateDialog(final int id) {

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (R.id.btnSelecionarDataInicial == id) {
                    dataInicialSelecionada = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    btnSelecionaDataInicial.setText(dataInicialSelecionada);

                }

                if (R.id.btnSelecionarDataFinal == id) {
                    dataFinalSelecionada = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    btnSelecionaDataFinal.setText(dataFinalSelecionada);
                    vendas = VendasDAO.filtraPorData(dataInicialSelecionada, dataFinalSelecionada, vendas);
                    populaListaView(vendas);
                }
            }
        };

        if (R.id.btnSelecionarDataInicial == id) {
            return new DatePickerDialog(this, listener, ano, (mes + 1), dia);
        }

        if (R.id.btnSelecionarDataFinal == id) {
            return new DatePickerDialog(this, listener, ano, (mes + 1), dia);
        }

        return null;
    }

    private void populaListaView(List<Venda> vendas) {

        ArrayAdapter<Venda> adapter = new ArrayAdapter<Venda>(this, android.R.layout.simple_list_item_1, vendas);
        lsVendas.setAdapter(adapter);

    }

    public void limparListView(View view) {
        vendas.clear();
        vendas = VendasDAO.criaListaVendas();
        populaListaView(vendas);
    }

    public void exibirGrafico(View view) {

        Intent intent = new Intent(MainActivity.this, GraficoActivity.class);
        intent.putExtra("dataInicio", UtilData.retornaData(dataInicialSelecionada));
        intent.putExtra("dataFinal", UtilData.retornaData(dataFinalSelecionada));
        DadosInstance.getInstance().setVendas(vendas);
        startActivity(intent);


    }
}
