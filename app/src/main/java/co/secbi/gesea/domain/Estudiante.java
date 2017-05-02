package co.secbi.gesea.domain;

/**
 * Created by JulioC on 4/30/17.
 */

public class Estudiante {

    String ID_Estudiante;
    String nombre_completo;

    public Estudiante(String ID_Estudiante, String nombre_completo) {
        this.ID_Estudiante = ID_Estudiante;
        this.nombre_completo = nombre_completo;
    }

    public String getID_Estudiante() {
        return ID_Estudiante;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }


}
