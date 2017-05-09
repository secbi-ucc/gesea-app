package co.secbi.gesea.domain;

/**
 * Created by JulioC on 5/2/17.
 */

public class DiaSemana {

    String Dia_Actividad;
    Horario Horario;

    public DiaSemana(String dia_Actividad, co.secbi.gesea.domain.Horario horario) {
        Dia_Actividad = dia_Actividad;
        Horario = horario;
    }

    public String getDia_Actividad() {
        return Dia_Actividad;
    }

    public co.secbi.gesea.domain.Horario getHorario() {
        return Horario;
    }


}
