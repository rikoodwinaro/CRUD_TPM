package com.example.crud.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = DataCrypto.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataCryptoDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase inidb(Context context){
        if (appDatabase==null)
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "dbCrypto").allowMainThreadQueries().build();
        return appDatabase;
    }
}
