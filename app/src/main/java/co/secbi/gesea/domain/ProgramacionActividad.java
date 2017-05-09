package co.secbi.gesea.domain;

/**
 * Created by JulioC on 5/2/17.
 */

public class ProgramacionActividad {



    String id;
    String Hora_Inicio;
    String Hora_Final;
    String Dia_Actividad;
    String tipo_actividad;

    public ProgramacionActividad(String id, String hora_Inicio, String hora_Final, String dia_Actividad, String tipo_actividad) {
        this.id = id;
        Hora_Inicio = hora_Inicio;
        Hora_Final = hora_Final;
        Dia_Actividad = dia_Actividad;
        this.tipo_actividad = tipo_actividad;
    }

    public String getId() {
        return id;
    }
    public String getHora_Inicio() {
        return Hora_Inicio;
    }

    public String getHora_Final() {
        return Hora_Final;
    }

    public String getDia_Actividad() {
        return Dia_Actividad;
    }

    public String getTipo_actividad() {
        return tipo_actividad;
    }

}
