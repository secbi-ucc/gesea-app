package co.secbi.gesea.io;

/**
 * Created by uzi200 on 9/21/15.
 */
public class ApiConstants {

    //public static final String BASE_URL = "https://gesea.secbi.co";
    public static final String BASE_URL = "http://192.168.1.7:8000";
    public static final String PATH_VERSION = "/api";
    public static final String PATH_ASISTENCIA= "/asistencia/lista";
    public static final String PATH_PROGRAMACION= "/programacion";
    public static final String PATH_LOGIN = PATH_VERSION + "/login/";
    public static final String PATH_LOGOUT = PATH_VERSION + "/logout/";
    public static final String PATH_USER = PATH_VERSION + "/user/";
    public static final String PARAM_FORMAT = "format";
    public static final String VALUE_JSON = "json";
    public static final String URL_ASISTENCIA_LIST = PATH_VERSION +  PATH_ASISTENCIA;
    public static final String URL_PROGRAMACION_LIST = PATH_VERSION +  PATH_PROGRAMACION + "/?" + PARAM_FORMAT + "=" + VALUE_JSON;

}
