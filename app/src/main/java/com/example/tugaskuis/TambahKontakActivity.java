package com.example.tugaskuis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tugaskuis.database.Kontak;
import com.example.tugaskuis.database.KontakDatabase;

import java.util.concurrent.Executors;

public class TambahKontakActivity extends AppCompatActivity {

    EditText etNama, etNoHp;
    ImageButton btnHome;
    Button btnTambahKontak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kontak);

        etNama = findViewById(R.id.etNama);
        etNoHp = findViewById(R.id.etNoHp);
        btnHome = findViewById(R.id.btnHome);
        btnTambahKontak = findViewById(R.id.btnTambahKontak);

        btnHome.setOnClickListener(v -> startActivity(new Intent(TambahKontakActivity.this, HomeActivity.class)));

        btnTambahKontak.setOnClickListener(v -> {
            String nama = etNama.getText().toString();
            String noHp = etNoHp.getText().toString();
            if (nama.isEmpty() || noHp.isEmpty()) {
                Toast.makeText(this, "Nama dan No HP tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            Executors.newSingleThreadExecutor().execute(this::addKontak);
            startActivity(new Intent(TambahKontakActivity.this, HomeActivity.class));
        });
    }

    void addKontak() {
        String nama = etNama.getText().toString();
        String noHp = etNoHp.getText().toString();
        Kontak kontak = new Kontak();
        kontak.nama = nama;
        kontak.noHp = noHp;
        KontakDatabase contactDatabase = Room.databaseBuilder(getApplicationContext(),
                KontakDatabase.class, "database-name").build();

        contactDatabase.kontakDao().insert(kontak);
    }
}