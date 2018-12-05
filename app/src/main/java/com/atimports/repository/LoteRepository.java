package com.atimports.repository;


import android.content.Context;

import com.atimports.dao.LoteDAO;
import com.atimports.database.ATImportsDataBase;
import com.atimports.model.Lote;

import java.util.List;

import io.reactivex.Flowable;

public class LoteRepository{

    private LoteDAO loteDAO;
    private static LoteRepository dsInstance;


    public LoteRepository(Context context) {
        this.loteDAO = ATImportsDataBase.getInstance(context).loteDAO();
    }

    public static LoteRepository getInstance(Context context){
        if(null == dsInstance){
            dsInstance = new LoteRepository(context);
        }
        return dsInstance;
    }

    public Flowable<Lote> getLoteById(Integer id) {

        return loteDAO.getLoteById(id);
    }

    public Flowable<List<Lote>> getLoteByProduto(String produto) {

        return loteDAO.getLoteByProduto(produto);
    }

    public Flowable<List<Lote>> getAllLote() {

        return loteDAO.getAllLote();
    }

    public void insertLote(Lote... lote) {

        loteDAO.insertLote(lote);
    }

    public void updateLote(Lote... lote) {

        loteDAO.updateLote(lote);
    }

    public void deleteLote(Lote... lote) {

        loteDAO.deleteLote(lote);
    }


}
