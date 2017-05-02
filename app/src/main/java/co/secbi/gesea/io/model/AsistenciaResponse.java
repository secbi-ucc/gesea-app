package co.secbi.gesea.io.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import co.secbi.gesea.domain.Asistencia;


public class AsistenciaResponse {

    @SerializedName(JsonKeys.ASISTENCIA_RESULTS)
    ZonasResults results;


    private class ZonasResults {

        @SerializedName(JsonKeys.ASISTENCIA_ARRAY)
        ArrayList<Asistencia> zonas;
    }


    public ArrayList<Asistencia> getZonas(){
        return results.zonas;
    }


}