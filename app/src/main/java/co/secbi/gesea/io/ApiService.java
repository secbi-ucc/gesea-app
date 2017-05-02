package co.secbi.gesea.io;

import co.secbi.gesea.io.model.AsistenciaResponse;
import co.secbi.gesea.io.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by uzi200 on 9/21/15.
 */

public interface ApiService {

   @GET(ApiConstants.URL_ASISTENCIA_LIST)
   Call<AsistenciaResponse> getAsistencia();

   @FormUrlEncoded
   @POST(ApiConstants.PATH_LOGIN)
   Call<LoginResponse> login(@Field("username") String username, @Field("password")String password);

   @POST(ApiConstants.PATH_LOGOUT)
   Call<LoginResponse> logout();



}


