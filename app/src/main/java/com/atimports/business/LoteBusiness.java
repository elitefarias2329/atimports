package com.atimports.business;


import com.atimports.model.Lote;

import java.util.List;

import io.reactivex.Flowable;

public interface LoteBusiness {


    Flowable<Lote> getLoteById(Integer id);

    Flowable<List<Lote>> getAllLote();

    void insertLote(Lote... lote);

    void updateLote(Lote... lote);

    void deleteLote(Lote... lote);
}
