package com.atimports.business;


import com.atimports.model.Leilao;

import java.util.List;

import io.reactivex.Flowable;

public interface LeilaoBusiness {


    Flowable<Leilao> getLeilaoById(Integer id);

    Flowable<List<Leilao>> getAllLeilao();

    void insertLeilao(Leilao... leilao);

    void updateLeilao(Leilao... leilao);

    void deleteLeilao(Leilao... leilao);
}
