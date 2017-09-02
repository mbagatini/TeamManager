package apoio;

import java.sql.Time;
import java.text.*;
import java.util.Date;
import java.util.Locale;

public class Formatacao {

    static DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

    public static String retornaDecimalFormatado(double valor) {
        NumberFormat formatter = new DecimalFormat("#,##0.00");
        return (formatter.format(valor));
    }

    public static double retornaDecimalString(String valor) {
        try {
            return df.parse(valor).doubleValue();
        } catch (ParseException ex) {
            System.out.println("Não consegui converter para double " + valor);
            return 0;
        }
    }

    public static Date retornaDataString(String data_formatada) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date data = df.parse(data_formatada);
        return data;
    }

    public static String retornaDataFormatada(Date data) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String data_formatada = df.format(data);
        return data_formatada;
    }

    public static Time retornaHoraString(String hora_formatada) throws ParseException {

        DateFormat df = new SimpleDateFormat("HH:mm");
        Date data_hora = df.parse(hora_formatada);
        Time time = new Time(data_hora.getTime());
        return time;
    }

    public static String retornaHoraFormatada(Time hora) throws ParseException {
        DateFormat df = new SimpleDateFormat("HH:mm");
        String data_formatada = df.format(hora);
        return data_formatada;
    }

    public static String ajustaDataDMA(String data) {
        String dataFormatada = null;
        try {
            Date dataAMD = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataAMD);
        } catch (Exception e) {
            System.err.println(e);
        }
        return (dataFormatada);
    }

}
