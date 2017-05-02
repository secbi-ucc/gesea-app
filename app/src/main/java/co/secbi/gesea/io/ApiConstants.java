package co.secbi.gesea.io;

/**
 * Created by uzi200 on 9/21/15.
 */
public class ApiConstants {

    public static final String BASE_URL = "http://192.168.1.36:8000";
    public static final String PATH_VERSION = "/api";
    public static final String PATH_ASISTENCIA= "/asistencia";
    public static final String PATH_LOGIN = "/rest-auth/login/";
    public static final String PATH_LOGOUT = "/rest-auth/logout/";
    public static final String PARAM_FORMAT = "format";
    public static final String VALUE_JSON = "json";
    public static final String URL_ASISTENCIA_LIST = PATH_VERSION +  PATH_ASISTENCIA + "/?" + PARAM_FORMAT + "=" + VALUE_JSON;

}
