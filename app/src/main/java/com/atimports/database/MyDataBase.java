package com.atimports.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.atimports.dao.LeilaoDAO;
import com.atimports.model.Leilao;

import static com.atimports.database.MyDataBase.DATABASE_VERSION;


@Database(entities = Leilao.class, version = DATABASE_VERSION)
public abstract class MyDataBase extends RoomDatabase {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myDataBase";

    private static MyDataBase myDatabaseInstance;

    public abstract LeilaoDAO leilaoDAO();



    public static MyDataBase getInstance(Context context){

        if(null == myDatabaseInstance){

            myDatabaseInstance = Room.databaseBuilder(context, MyDataBase.class, DATABASE_NAME)
                                .fallbackToDestructiveMigration()
                                .build();
        }

        return myDatabaseInstance;
    }


}
