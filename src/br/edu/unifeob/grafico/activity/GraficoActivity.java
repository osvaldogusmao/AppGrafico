package br.edu.unifeob.grafico.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import br.edu.unifeob.grafico.dao.VendasDAO;
import br.edu.unifeob.grafico.entidades.Venda;
import br.edu.unifeob.grafico.infra.DadosInstance;
import com.example.grafico.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by osvaldogusmao on 03/06/14.
 */
public class GraficoActivity extends Activity {

    private Calendar dataInicio;
    private Calendar dataFim;
    private List<Venda> vendas;
    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafico);
        dataInicio = (Calendar) getIntent().getSerializableExtra("dataInicio");
        dataFim = (Calendar) getIntent().getSerializableExtra("dataFim");
        vendas = DadosInstance.getInstance().getVendas();
        webView = (WebView) findViewById(R.id.webView);

        String content = "<html>\n" +
                "  <head>\n" +
                "    <script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>\n" +
                "    <script type=\"text/javascript\">\n" +
                "      google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});\n" +
                "      google.setOnLoadCallback(drawChart);\n" +
                "      function drawChart() {\n" +
                "        var data = google.visualization.arrayToDataTable([\n";

        content += VendasDAO.preparaDadosParaGrafico(vendas);
        content += "        ]);\n" +
                " \n" +
                "        var options = {\n" +
                "          title: 'Vendas por período',\n" +
                "          hAxis: {title: 'Data', titleTextStyle: {color: 'red'}}\n" +
                "        };\n" +
                " \n" +
                "        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));\n" +
                "        chart.draw(data, options);\n" +
                "      }\n" +
                "    </script>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div id=\"chart_div\" style=\"width: 1000px; height: 500px;\"></div>\n" +
                "  </body>\n" +
                "</html>";

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.requestFocusFromTouch();
        webView.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "utf-8", null);

    }

}