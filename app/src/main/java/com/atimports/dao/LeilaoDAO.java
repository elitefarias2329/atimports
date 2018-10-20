package com.atimports.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.atimports.model.Leilao;

import java.util.List;

import io.reactivex.Flowable;

public interface LeilaoDAO {


    @Query("SELECT * FROM LEILAO WHERE ID = :id")
    Flowable<Leilao> getLeilaoById(Integer id);

    @Query("SELECT * FROM LEILAO")
    Flowable<List<Leilao>> getAllLeilao();

    @Insert
    void insertLeilao(Leilao... leilao);

    @Update
    void updateLeilao(Leilao... leilao);

    @Delete
    void deleteLeilao(Leilao... leilao);





}
