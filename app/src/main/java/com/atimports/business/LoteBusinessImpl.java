package com.atimports.business;


import com.atimports.dao.LoteDAO;
import com.atimports.model.Lote;

import java.util.List;

import io.reactivex.Flowable;

public class LoteBusinessImpl implements LoteBusiness {


    private LoteDAO loteDAO;

    private static LoteBusinessImpl dsInstance;

    public LoteBusinessImpl(LoteDAO loteDAO) {

        this.loteDAO = loteDAO;
    }

    public static LoteBusinessImpl getInstance(LoteDAO loteDAO){
        if(null == dsInstance){
            dsInstance = new LoteBusinessImpl(loteDAO);
        }
        return dsInstance;
    }

    @Override
    public Flowable<Lote> getLoteById(Integer id) {
        return loteDAO.getLoteById(id);
    }

    @Override
    public Flowable<List<Lote>> getAllLote() {
        return loteDAO.getAllLote();
    }

    @Override
    public void insertLote(Lote... lote) {
        loteDAO.insertLote(lote);
    }

    @Override
    public void updateLote(Lote... lote) {
        loteDAO.updateLote(lote);
    }

    @Override
    public void deleteLote(Lote... lote) {
        loteDAO.deleteLote(lote);
    }


}
