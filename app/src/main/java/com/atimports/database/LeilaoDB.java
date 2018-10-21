package com.atimports.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.atimports.dao.LeilaoDAO;
import com.atimports.model.Leilao;

import static com.atimports.database.LeilaoDB.DATABASE_VERSION;


@Database(entities = Leilao.class, version = DATABASE_VERSION, exportSchema = false)
public abstract class LeilaoDB extends RoomDatabase {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myDataBase";

    private static LeilaoDB dbInstance;

    public abstract LeilaoDAO leilaoDAO();


    public static LeilaoDB getInstance(Context context){

        if(null == dbInstance){
                    dbInstance = Room.databaseBuilder(context, LeilaoDB.class, DATABASE_NAME)
                                .fallbackToDestructiveMigration()
                                .build();
        }
        return dbInstance;
    }


}
