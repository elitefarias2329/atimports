package com.atimports.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.atimports.dao.LoteDAO;
import com.atimports.model.Lote;

import static com.atimports.database.ATImportsDataBase.DATABASE_VERSION;


@Database(entities = Lote.class, version = DATABASE_VERSION, exportSchema = false)
public abstract class ATImportsDataBase extends RoomDatabase {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ATImportsDataBase";

    public abstract LoteDAO loteDAO();
    private static ATImportsDataBase dbInstance;

    public static ATImportsDataBase getInstance(Context context){

        if(null == dbInstance){
            dbInstance = Room.databaseBuilder(context, ATImportsDataBase.class, DATABASE_NAME)
                             .fallbackToDestructiveMigration()
                             .build();
        }
        return dbInstance;
    }


}
