package com.example.kelasonline.model;

import com.google.gson.annotations.SerializedName;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class Dosen {

    @SerializedName("id_dosen")
    private String id_dosen;
    @SerializedName("nama")
    private String nama;
    @SerializedName("email")
    private String email;

    public String getId_dosen() {
        return id_dosen;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

}
