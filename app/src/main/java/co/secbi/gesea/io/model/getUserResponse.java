package co.secbi.gesea.io.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JulioC on 5/2/17.
 */

public class getUserResponse {
    @SerializedName("get_full_name")
    public String get_full_name;
    @SerializedName("email")
    public String email;
    @SerializedName("username")
    public String username;
}
