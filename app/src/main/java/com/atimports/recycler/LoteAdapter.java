package com.atimports.recycler;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atimports.R;
import com.atimports.activity.AddActivity;
import com.atimports.activity.MainActivity;
import com.atimports.model.Lote;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class LoteAdapter extends RecyclerView.Adapter{


    private static List<Lote> lotes;
    private static Context context;


    public LoteAdapter(Context context, List<Lote> lotes ){
        this.context = context;
        this.lotes = lotes;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_lote, parent, false);
        return new LoteViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int itemPosition) {

        LoteViewHolder holder = (LoteViewHolder) viewHolder;

        Lote lote = lotes.get(itemPosition);



        Glide.with(context)
             .load(lote.getPathFotoProduto())
             .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).skipMemoryCache(true).dontAnimate())
             .into(holder.ivFotoProduto)
             .waitForLayout();

        holder.tvItemProduto.setText(lote.getProduto());
        holder.tvItemQuantidade.setText(String.valueOf(lote.getQtd()));
        holder.tvItemcondicao.setText(lote.getCondicao());
        holder.tvItemDataOrdem.setText(lote.getDataOrdemCompra());
        holder.tvItemStatusOrdem.setText(lote.getStatusOrdemCompra());


    }

    @Override
    public int getItemCount() {

        if(null != lotes){
            return lotes.size();
        }
        return 0;
    }



    // INNER CLASS HOLDER
    public static class LoteViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        public final ImageView ivFotoProduto;
        public final TextView tvItemProduto;
        public final TextView tvItemQuantidade;
        public final TextView tvItemcondicao;
        public final TextView tvItemDataOrdem;
        public final TextView tvItemStatusOrdem;


        public LoteViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
            this.ivFotoProduto = view.findViewById(R.id.iv_foto_produto);
            this.tvItemProduto = view.findViewById(R.id.tv_item_produto);
            this.tvItemQuantidade = view.findViewById(R.id.tv_item_qtd);
            this.tvItemcondicao = view.findViewById(R.id.tv_item_condicao);
            this.tvItemDataOrdem = view.findViewById(R.id.tv_item_data_ordem);
            this.tvItemStatusOrdem = view.findViewById(R.id.tv_item_status_ordem);



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) v.getContext()).detalharLote(lotes.get(getAdapterPosition()));
                }
            });




            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ((MainActivity) v.getContext()).deleteLote(lotes.get(getAdapterPosition()));
                    return false;
                }
            });

        }

    }













}
