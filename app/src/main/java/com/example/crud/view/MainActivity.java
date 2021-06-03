package com.example.crud.view;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.entity.AppDatabase;
import com.example.crud.entity.DataCrypto;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContact.view {
    private AppDatabase appDatabase;
    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    private Button submit;
    private RecyclerView recyclerView;
    private EditText etCurrency, etPrice, etYear, etFounder;

    private int id = 0;
    boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit = findViewById(R.id.submit);
        etCurrency = findViewById(R.id.etCurrency);
        etPrice = findViewById(R.id.etPrice);
        etYear = findViewById(R.id.etYear);
        etFounder = findViewById(R.id.etFounder);
        recyclerView = findViewById(R.id.rc_main);

        appDatabase = AppDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter = new MainPresenter(this);
        mainPresenter.readData(appDatabase);
        submit.setOnClickListener(this);
    }

    @Override
    public void successAdd() {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this, "Berhasil Menghapus Data", Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etCurrency.setText("");
        etFounder.setText("");
        etYear.setText("");
        etPrice.setText("");
        submit.setText("Submit");
    }

    public void getData(List<DataCrypto> list) {
        mainAdapter = new MainAdapter(this, list, this);
        recyclerView.setAdapter(mainAdapter);
    }

    public void editData(DataCrypto item) {
        etCurrency.setText(item.getCurrency());
        etPrice.setText(item.getPrice());
        etYear.setText(item.getYear());
        etFounder.setText(item.getFounder());
        id = item.getId();
        edit = true;
        submit.setText("EDIT DATA");
    }

    @Override
    public void deleteData(DataCrypto item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        mainPresenter.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_menu_delete)
                .show();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.submit) {
            if (etCurrency.getText().toString().equals("") || etPrice.getText().toString().equals("") ||
                    etYear.getText().toString().equals("") || etFounder.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Harap isi Semua data", Toast.LENGTH_SHORT).show();
            } else {
                if (!edit) {
                    mainPresenter.insertData(etCurrency.getText().toString(), etPrice.getText().toString(), etYear.getText().toString(),
                            etFounder.getText().toString(), appDatabase);
                } else {
                    mainPresenter.editData(etCurrency.getText().toString(), etPrice.getText().toString(), etYear.getText().toString(),
                            etFounder.getText().toString(), id, appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
    }
}