package com.atimports.business;


import com.atimports.dao.LeilaoDAO;
import com.atimports.model.Leilao;

import java.util.List;

import io.reactivex.Flowable;

public class LeilaoBusinessImpl implements LeilaoBusiness {


    private LeilaoDAO leilaoDAO;

    private static LeilaoBusinessImpl dsInstance;

    public LeilaoBusinessImpl(LeilaoDAO leilaoDAO) {
        this.leilaoDAO = leilaoDAO;
    }

    public static LeilaoBusinessImpl getInstance(LeilaoDAO leilaoDAO){
        if(null == dsInstance){
            dsInstance = new LeilaoBusinessImpl(leilaoDAO);
        }
        return dsInstance;
    }

    @Override
    public Flowable<Leilao> getLeilaoById(Integer id) {
        return leilaoDAO.getLeilaoById(id);
    }

    @Override
    public Flowable<List<Leilao>> getAllLeilao() {
        return leilaoDAO.getAllLeilao();
    }

    @Override
    public void insertLeilao(Leilao... leilao) {
        leilaoDAO.insertLeilao(leilao);
    }

    @Override
    public void updateLeilao(Leilao... leilao) {
        leilaoDAO.updateLeilao(leilao);
    }

    @Override
    public void deleteLeilao(Leilao... leilao) {
        leilaoDAO.deleteLeilao(leilao);
    }


}
