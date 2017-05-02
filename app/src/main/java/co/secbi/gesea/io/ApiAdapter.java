package co.secbi.gesea.io;

import android.content.Context;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.net.CookieManager;
import java.net.CookiePolicy;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by uzi200 on 9/21/15.
 */
public class ApiAdapter {

    private static ApiService API_SERVICE;


    public static ApiService getApiService (Context c){


        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(c));

        builder.addInterceptor(logging);
        builder.cookieJar(cookieJar);

        client = builder.build();





        if(API_SERVICE == null){
            Retrofit adapter = new  Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client) // VERY VERY IMPORTANT
                    .build();

            API_SERVICE = adapter.create(ApiService.class);
        }

        return API_SERVICE;
    }


}
