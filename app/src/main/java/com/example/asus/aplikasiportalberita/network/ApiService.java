package com.example.asus.aplikasiportalberita.network;

import com.example.asus.aplikasiportalberita.Response.ResponseBerita;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    //get end_point dari folder portal berita
    @GET("tampil_berita.php")
    Call<ResponseBerita> getShowAllBerita();
}
