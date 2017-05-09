package co.secbi.gesea.domain;

/**
 * Created by JulioC on 5/2/17.
 */

public class Horario {

    String Hora_Inicio;
    String Hora_Final;
    String get_time_diff;

    public Horario(String hora_Inicio, String hora_Final, String get_time_diff) {
        Hora_Inicio = hora_Inicio;
        Hora_Final = hora_Final;
        this.get_time_diff = get_time_diff;
    }

    public String getHora_Inicio() {
        return Hora_Inicio;
    }

    public String getHora_Final() {
        return Hora_Final;
    }

    public String getGet_time_diff() {
        return get_time_diff;
    }


}
