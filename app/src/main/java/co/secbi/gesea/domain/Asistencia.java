package co.secbi.gesea.domain;

/**
 * Created by JulioC on 4/29/17.
 */

public class Asistencia {

    String id;
    Estudiante estudiante;
    Integer n_horas;


    public Asistencia(String id, Integer n_horas) {

        this.id = id;

        this.n_horas = n_horas;
    }

    public String getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setN_horas(Integer n_horas) {
        this.n_horas = n_horas;
    }

    public Integer getN_horas() {
        return n_horas;
    }

}
