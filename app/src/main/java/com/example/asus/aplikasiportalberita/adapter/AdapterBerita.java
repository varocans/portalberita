package com.example.asus.aplikasiportalberita.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.aplikasiportalberita.MainActivity;
import com.example.asus.aplikasiportalberita.MyConstant;
import com.example.asus.aplikasiportalberita.R;
import com.example.asus.aplikasiportalberita.Response.BeritaItem;
import com.example.asus.aplikasiportalberita.activities.DetailBerita;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.asus.aplikasiportalberita.MyConstant.URL_API_IMAGE;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.ViewHolder> {

    Context context;
    List<BeritaItem> listBerita;

    public AdapterBerita(Context context, List<BeritaItem> listBerita) {
        this.context = context;
        this.listBerita = listBerita;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_berita, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.tvJudulBerita.setText(listBerita.get(position).getJudulBerita());
        holder.tvTglPosting.setText(listBerita.get(position).getTanggalPosting());
        holder.tvPenulis.setText(listBerita.get(position).getPenulis());

        final String images = URL_API_IMAGE + listBerita.get(position).getFoto();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailBerita.class);
                intent.putExtra(DetailBerita.EXTRA_JUDUL, listBerita.get(position).getJudulBerita());
                intent.putExtra(DetailBerita.EXTRA_GAMBAR_BERITA, images);
                intent.putExtra(DetailBerita.EXTRA_PENULIS, listBerita.get(position).getPenulis());
                intent.putExtra(DetailBerita.EXTRA_ISI_BERITA, listBerita.get(position).getIsiBerita());
                intent.putExtra(DetailBerita.EXTRA_TGL_POSTING, listBerita.get(position).getTanggalPosting());
                context.startActivity(intent);
            }
        });

        Picasso.get().load(images).into(holder.ivBerita);
    }

    @Override
    public int getItemCount() {
        return listBerita.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_berita)
        ImageView ivBerita;
        @BindView(R.id.tv_judul_berita)
        TextView tvJudulBerita;
        @BindView(R.id.tv_tgl_posting)
        TextView tvTglPosting;
        @BindView(R.id.tv_penulis)
        TextView tvPenulis;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}