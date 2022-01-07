package com.example.tugaskuis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tugaskuis.database.Kontak;
import com.example.tugaskuis.database.KontakDatabase;

import java.util.List;
import java.util.concurrent.Executors;

public class HomeActivity extends AppCompatActivity {

    ImageButton btnLogout;
    Button btnTambah;

    RecyclerView rvKontak;

    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPrefManager = new SharedPrefManager(this);

        btnLogout = findViewById(R.id.btnLogout);
        btnTambah = findViewById(R.id.btnTambah);
        rvKontak = findViewById(R.id.rvKontak);

        btnLogout.setOnClickListener(v -> {
            sharedPrefManager.saveIsLogin(false);
            Intent i = new Intent(HomeActivity.this, MainActivity.class);
            finishAffinity();
            startActivity(i);
        });

        btnTambah.setOnClickListener(v -> {
            Intent i = new Intent(HomeActivity.this, TambahKontakActivity.class);
            startActivity(i);
        });

        Executors.newSingleThreadExecutor().execute(this::setDaftarKontak);
    }

    void setDaftarKontak() {
        KontakDatabase contactDatabase = Room.databaseBuilder(getApplicationContext(),
                KontakDatabase.class, "database-name").build();

        List<Kontak> kontaks = contactDatabase.kontakDao().getAll();

        rvKontak.setHasFixedSize(true);
        rvKontak.setLayoutManager(new LinearLayoutManager(this));
        rvKontak.setAdapter(new DaftarKontakAdapter(kontaks));
    }
}