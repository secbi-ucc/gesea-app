package co.secbi.gesea.io;

import com.franmontiel.persistentcookiejar.cache.CookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import co.secbi.gesea.LoginActivity;
import co.secbi.gesea.io.model.AsistenciaResponse;
import co.secbi.gesea.io.model.LoginResponse;
import co.secbi.gesea.io.model.ProgramacionResponse;
import co.secbi.gesea.io.model.getUserResponse;
import okhttp3.Cookie;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by uzi200 on 9/21/15.
 */

public interface ApiService {


   @GET()
   Call<AsistenciaResponse> getAsistencia(@Url String url);

   @GET(ApiConstants.URL_PROGRAMACION_LIST)
   Call<ProgramacionResponse> getProgramacion();

   @FormUrlEncoded
   @PUT()
   Call<AsistenciaResponse> setHoras(
           @Header("X-CSRFToken") String CSRFToken,
           @Url String url,
           @Field("n_horas") Integer n_horas);



   @FormUrlEncoded
   @POST(ApiConstants.PATH_LOGIN)
   Call<LoginResponse> login(@Field("username") String username, @Field("password")String password);

   @POST(ApiConstants.PATH_LOGOUT)
   Call<LoginResponse> logout();

   @GET(ApiConstants.PATH_USER)
   Call<getUserResponse> getUser();

}


