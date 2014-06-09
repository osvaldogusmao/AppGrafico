package br.edu.unifeob.grafico.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class UtilData {


    public static Calendar retornaData(String data) {

        Calendar dataFinal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataFinal.setTime(format.parse(data));
            return dataFinal;
        } catch (ParseException e) {
            return null;
        }
    }

    public static Calendar converterStringCalendar(String data_str) {

        String[] data = data_str.split("/");

        Calendar calendarFormat = Calendar.getInstance();
        calendarFormat.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data[0]));
        calendarFormat.set(Calendar.MONTH, Integer.parseInt(data[1]));
        calendarFormat.set(Calendar.YEAR, Integer.parseInt(data[2]));

        return calendarFormat;
    }

    public static String converterEmPadraoBR(Calendar calendar) {
        Integer dia = calendar.get(Calendar.DAY_OF_MONTH);
        Integer mes = calendar.get(Calendar.MONTH);
        Integer ano = calendar.get(Calendar.YEAR);
        String data = dia + "/" + mes + "/" + ano;
        return data;
    }

}
