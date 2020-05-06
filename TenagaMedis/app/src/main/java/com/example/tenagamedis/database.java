package com.example.tenagamedis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class database extends AppCompatActivity {
    //initialize Variable
    EditText etnama;
    EditText etno;
    EditText etsakit;
    Spinner spinner;
    Button btdaftar;
    String etspinner;
    private SQLiteDatabase mDatabase;
    private DaftarAdapter mAdapter;

    DatabaseHelper databaseHelper;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DaftarAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);



        //Assign Variable
        etnama = findViewById(R.id.et_nama);
        etno = findViewById(R.id.et_no);
        etsakit = findViewById(R.id.et_sakit);
        spinner = findViewById(R.id.spinner);
        btdaftar = findViewById(R.id.daftar);

        String[] items = new String[]{"Klinik Pratama Kosasih Panjang", "Klinik Pratama Kosasih Teluk Betung", "Klinik Pratama Kosasih Amanah"
                , "Klinik Rawat Inap Dokter Spesialis Mitra Kosasih", "KLINIK KOSASIH PASAR TUGU", "Klinik Sehat Kosasih Lampung", "Klinik Kosasih Tirtayasa"
                , "Klinik Kosasih Urip", "Klinik Pratama Kosasih Palapa", "Klinik Kosasih Kemiling", "Klinik Rawat Inap Kosasih Rajabasa"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        etspinner = spinner.getSelectedItem().toString();

        //Initialize DatabaseHelper
  //      databaseHelper = new DatabaseHelper(database.this);

        //Initialize ArrayAdapter
 //       arrayAdapter = new ArrayAdapter(database.this,
 //               android.R.layout.simple_list_item_1,arrayList);

        btdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
//                // Get text from EditText
//                String text = etnama.getText().toString();
//                if (!text.isEmpty()){
//                    if(databaseHelper.addText(text)){
//                        etnama.setText("");//Cleae EditText
//                        //Display Toast Message
//                        Toast.makeText(getApplicationContext(), "Data Inserted..."
//                                ,Toast.LENGTH_SHORT).show();
//                        //Clear ArrayList
//                        arrayList.clear();
//                        arrayList.addAll(databaseHelper.getAllText());
//                        //Refresh ListView Data
//                        arrayAdapter.notifyDataSetChanged();
//                        listView.invalidateViews();
//                        listView.refreshDrawableState();
//                    }
                }
        });
    }

    private void addItem() {

        if(etnama.getText().toString().length() == 0 ||
                etno.getText().toString().length() == 0 ||
                etsakit.getText().toString().length() == 0 ||
                etspinner == ""){
            return;
        }

        String nama = etnama.getText().toString();
        String nomor = etno.getText().toString();
        String penyakit = etsakit.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(DaftarContract.DaftarEntry.COLUMN_1, nama);
        cv.put(DaftarContract.DaftarEntry.COLUMN_2, nomor);
        cv.put(DaftarContract.DaftarEntry.COLUMN_3, penyakit);
        cv.put(DaftarContract.DaftarEntry.COLUMN_4, etspinner);

        mDatabase.insert(DaftarContract.DaftarEntry.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());

        etnama.getText().clear();
        etno.getText().clear();
        etsakit.getText().clear();

    }

    private void removeItem(long id) {
        mDatabase.delete(DaftarContract.DaftarEntry.TABLE_NAME,
                DaftarContract.DaftarEntry._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
    }



    private Cursor getAllItems() {
        return mDatabase.query(
                DaftarContract.DaftarEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DaftarContract.DaftarEntry.COLUMN_5 + " DESC"
        );
    }
}
