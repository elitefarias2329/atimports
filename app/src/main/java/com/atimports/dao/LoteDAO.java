package com.atimports.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.atimports.model.Lote;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface LoteDAO {


    @Query("SELECT * FROM LOTE WHERE ID = :id")
    Flowable<Lote> getLoteById(Integer id);

    @Query("SELECT * FROM LOTE WHERE PRODUTO LIKE '%' || :produto || '%'")
    Flowable<List<Lote>> getLoteByProduto(String produto);

    @Query("SELECT * FROM LOTE ORDER BY DATA_ORDEM_COMPRA DESC, PRODUTO ASC")
    Flowable<List<Lote>> getAllLote();

    @Insert
    void insertLote(Lote... lote);

    @Update
    void updateLote(Lote... lote);

    @Delete
    void deleteLote(Lote... lote);





}
