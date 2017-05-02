package co.secbi.gesea.io.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JulioC on 5/1/17.
 */

public class LoginResponse {
    @SerializedName("key")
    public String key;

    @SerializedName("non_field_errors")
    public String error;

}
