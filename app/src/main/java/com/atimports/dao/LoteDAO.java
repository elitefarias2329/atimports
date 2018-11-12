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


    @Query("SELECT * FROM Lote WHERE ID = :id")
    Flowable<Lote> getLoteById(Integer id);

    @Query("SELECT * FROM Lote")
    Flowable<List<Lote>> getAllLote();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLote(Lote... lote);

    @Update
    void updateLote(Lote... lote);

    @Delete
    void deleteLote(Lote... lote);





}
