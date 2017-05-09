package co.secbi.gesea.domain;

/**
 * Created by JulioC on 5/2/17.
 */

public class Usuario {

    String get_full_name;
    String email;
    String username;

    public Usuario(String get_full_name, String email, String username) {
        this.get_full_name = get_full_name;
        this.email = email;
        this.username = username;
    }

    public String getGet_full_name() {
        return get_full_name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }



}
