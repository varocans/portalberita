package com.example.asus.aplikasiportalberita;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.asus.aplikasiportalberita.Response.BeritaItem;
import com.example.asus.aplikasiportalberita.Response.ResponseBerita;
import com.example.asus.aplikasiportalberita.adapter.AdapterBerita;
import com.example.asus.aplikasiportalberita.network.ApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.asus.aplikasiportalberita.network.ConfigRetrofit.getInstance;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //untuk fix ukuran gambar agar tidak pecah
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        //method untuk menampilkan data beritanya dihalaman utama
        showBerita();
    }

    private void showBerita() {

        //siapkan request ke url nya kedatabase
        ApiService service = getInstance();

        //kirim request ke url nya kedatabase
        Call<ResponseBerita> beritaCall = service.getShowAllBerita();
        //isi Callback
        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {

                // buat kondisi
                if (response.isSuccessful()){
                    Log.d("TAG",response.toString());

                    //tampung berita kedalam list dan get berita nya
                    List<BeritaItem> berita = response.body().getBerita();
                    boolean status = response.body().isStatus();

                    //kalo status nya benar tampilan datanya kedalam recyclerview
                    if (status){
                        AdapterBerita adapter = new AdapterBerita(MainActivity.this, berita);
                        recyclerview.setAdapter(adapter);
                    } else {
                        Toast.makeText(MainActivity.this, "Berita Tidak ada", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something Problem", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}