package co.secbi.gesea.io.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import co.secbi.gesea.domain.Actividad;
import co.secbi.gesea.domain.Asistencia;
import co.secbi.gesea.domain.DiaSemana;
import co.secbi.gesea.domain.Programacion;

/**
 * Created by JulioC on 5/2/17.
 */

public class ProgramacionResponse {

    @SerializedName(JsonKeys.PROGRAMACION_RESULTS)
    ProgramacionResults results;


    private class ProgramacionResults {

        @SerializedName(JsonKeys.PROGRAMACION_ARRAY)
        ArrayList<Programacion> programacion;
    }


    public ArrayList<Programacion> getProgramacion(){
        return results.programacion;
    }

}
