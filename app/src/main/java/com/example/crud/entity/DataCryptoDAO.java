package com.example.crud.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface DataCryptoDAO {
    @Insert
    Long insertData(DataCrypto dataCrypto);

    @Query("Select * from crypto_db")
    List<DataCrypto> getData();

    @Update
    int updateData(DataCrypto item);

    @Delete
    void deleteData(DataCrypto item);

}
