package com.example.asus.aplikasiportalberita.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.aplikasiportalberita.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailBerita extends AppCompatActivity {

    //buat variable penampung untuk terima datanya
    public static final String EXTRA_JUDUL = "judul";
    public static final String EXTRA_GAMBAR_BERITA = "gambar";
    public static final String EXTRA_TGL_POSTING = "tgl_posting";
    public static final String EXTRA_PENULIS = "penulis";
    public static final String EXTRA_ISI_BERITA = "isi";

    @BindView(R.id.ivGambarBerita)
    ImageView ivGambarBerita;
    @BindView(R.id.tv_tgl_posting_detail)
    TextView tvTglPostingDetail;
    @BindView(R.id.tv_penulis_detail)
    TextView tvPenulisDetail;
    @BindView(R.id.wv_content_berita)
    WebView wvContentBerita;
    @BindView(R.id.tv_judul_berita)
    TextView tvJudulBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        ButterKnife.bind(this);

        showBeritaDetail();

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void showBeritaDetail() {

        //get data yang dikirim dari adapter
        String judul = getIntent().getStringExtra(EXTRA_JUDUL);
        String gambar = getIntent().getStringExtra(EXTRA_GAMBAR_BERITA);
        String penulis = getIntent().getStringExtra(EXTRA_PENULIS);
        String isiBerita = getIntent().getStringExtra(EXTRA_ISI_BERITA);
        String tgl = getIntent().getStringExtra(EXTRA_TGL_POSTING);

        //set support action bar nya sesuai judul berita
        getSupportActionBar().setTitle(judul);

        //menampilkan data nya ke text view
        tvTglPostingDetail.setText(tgl);
        tvPenulisDetail.setText(penulis);
        tvJudulBerita.setText(judul);


        //load images halaman detail dengan menggunakan library picasso
        Picasso.get().load(gambar).into(ivGambarBerita);

        //set isi berita dari html ke webview
        wvContentBerita.getSettings().setJavaScriptEnabled(true);
        wvContentBerita.loadData(isiBerita, "text/html; charset=utf-8", "utf-8");
    }

}