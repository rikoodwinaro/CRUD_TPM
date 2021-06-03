package com.example.crud.view;

import android.os.AsyncTask;
import android.util.Log;

import com.example.crud.entity.AppDatabase;
import com.example.crud.entity.DataCrypto;
import com.example.crud.entity.DataCrypto;

import java.util.List;

public class MainPresenter implements MainContact.presenter {
    private MainContact.view view;

    public MainPresenter(MainContact.view view){
        this.view = view;
    }

    class InsertData extends AsyncTask<Void,Void,Long> {
        private AppDatabase appDatabase;
        private DataCrypto dataCrypto;
        public InsertData(AppDatabase appDatabase, DataCrypto dataCrypto) {
            this.appDatabase = appDatabase;
            this.dataCrypto = dataCrypto;
        }

        protected Long doInBackground(Void... voids){
            return appDatabase.dao().insertData(dataCrypto);
        }

        protected void onPostExecute(Long along){
            super.onPostExecute(along);
            view.successAdd();
        }
    }
    @Override
    public void insertData(String currency, String price, String year, String founder, AppDatabase database) {
        final DataCrypto dataCrypto =  new DataCrypto();
        dataCrypto.setCurrency(currency);
        dataCrypto.setPrice(price);
        dataCrypto.setYear(year);
        dataCrypto.setFounder(founder);
        new InsertData(database,dataCrypto).execute();
    }

    @Override
    public void readData(AppDatabase database) {
        List<DataCrypto> list;
        list = database.dao().getData();
        view.getData(list);
    }

    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase appDatabase;
        private DataCrypto dataCrypto;

        public EditData(AppDatabase appDatabase, DataCrypto dataCrypto){
            this.appDatabase = appDatabase;
            this.dataCrypto = dataCrypto;
        }

        protected Integer doInBackground(Void... voids){
            return  appDatabase.dao().updateData(dataCrypto);
        }

        protected void  onPostExecute(Integer integer) {
           super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute : "+integer);
            view.successAdd();
        }
    }

    @Override
    public void editData(String currency, String price, String year, String founder, int id, AppDatabase database) {
        final DataCrypto dataCrypto = new DataCrypto();
        dataCrypto.setCurrency(currency);
        dataCrypto.setPrice(price);
        dataCrypto.setYear(year);
        dataCrypto.setFounder(founder);
        dataCrypto.setId(id);
        new EditData(database,dataCrypto).execute();
    }

    class  DeleteData extends  AsyncTask<Void,Void,Long>{
        private AppDatabase appDatabase;
        private DataCrypto dataCrypto;

        public DeleteData(AppDatabase appDatabase, DataCrypto dataCrypto){
            this.appDatabase = appDatabase;
            this.dataCrypto = dataCrypto;
        }

        protected Long doInBackground(Void... voids){
            appDatabase.dao().deleteData(dataCrypto);
            return null;
        }

        protected void  onPostExecute(Long along) {
            super.onPostExecute(along);
            view.successDelete();
        }
    }

    @Override
    public void deleteData(DataCrypto dataCrypto, AppDatabase appDatabase) {
        new DeleteData(appDatabase, dataCrypto).execute();
    }
}
