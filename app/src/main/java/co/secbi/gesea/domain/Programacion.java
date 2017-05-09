package co.secbi.gesea.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JulioC on 5/2/17.
 */

public class Programacion {

    Integer id;
    Actividad actividad;

    List<DiaSemana> Dia_semana = new ArrayList<DiaSemana>();


    public Programacion(Integer id, Actividad actividad,  List<DiaSemana> dia_semana) {
        this.id = id;
        this.actividad = actividad;
        Dia_semana = dia_semana;
    }

    public Integer getId() {
        return id;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public  List<DiaSemana> getDia_semana() {
        return Dia_semana;
    }
}

