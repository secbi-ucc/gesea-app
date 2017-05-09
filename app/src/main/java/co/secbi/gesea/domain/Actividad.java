package co.secbi.gesea.domain;

/**
 * Created by JulioC on 5/2/17.
 */

public class Actividad {


    String Codigo_actividad;
    String tipo_actividad;
    String Estado_actividad;
    Integer Cupo_Actividad;

    public Actividad(String codigo_actividad, String tipo_actividad, String estado_actividad, Integer cupo_Actividad) {
        Codigo_actividad = codigo_actividad;
        this.tipo_actividad = tipo_actividad;
        Estado_actividad = estado_actividad;
        Cupo_Actividad = cupo_Actividad;
    }


    public String getCodigo_actividad() {
        return Codigo_actividad;
    }

    public String getTipo_actividad() {
        return tipo_actividad;
    }

    public String getEstado_actividad() {
        return Estado_actividad;
    }

    public Integer getCupo_Actividad() {
        return Cupo_Actividad;
    }


}
