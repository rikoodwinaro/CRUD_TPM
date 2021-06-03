package com.example.crud.view;

import android.view.View;

import com.example.crud.entity.AppDatabase;
import com.example.crud.entity.DataCrypto;
import com.example.crud.entity.DataCrypto;

import java.util.List;

public interface MainContact {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataCrypto> list);
        void editData(DataCrypto item);
        void deleteData(DataCrypto item);
    }

    interface presenter{
        void insertData(String currency, String price, String year, String founder, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String currency, String price, String year, String founder, int id, AppDatabase database);
        void deleteData(DataCrypto dataMovie, AppDatabase appDatabase);
    }
}
