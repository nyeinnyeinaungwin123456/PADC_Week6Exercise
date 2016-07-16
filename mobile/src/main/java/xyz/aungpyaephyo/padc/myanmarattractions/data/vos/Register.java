package xyz.aungpyaephyo.padc.myanmarattractions.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nyein Nyein on 7/16/2016.
 */
public class Register {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("date_of_birth")
    private String date_of_birth;

    @SerializedName("country_of_origin")
    private String coutry_of_origin;

    public Register() {
    }

    public Register(String name, String email, String password, String date_of_birth, String coutry_of_origin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.date_of_birth = date_of_birth;
        this.coutry_of_origin = coutry_of_origin;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getCoutry_of_origin() {
        return coutry_of_origin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setCoutry_of_origin(String coutry_of_origin) {
        this.coutry_of_origin = coutry_of_origin;
    }
}
