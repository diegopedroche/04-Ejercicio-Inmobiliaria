package com.example.a04ejercicioinmobiliaria;

import android.content.Intent;
import android.os.Bundle;

import com.example.a04ejercicioinmobiliaria.modelos.Piso;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import com.example.a04ejercicioinmobiliaria.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Piso> pisos;
    private ActivityResultLauncher<Intent> launcherCrearPisos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pisos = new ArrayList<Piso>();
        inicializaLaunchers();

        setSupportActionBar(binding.toolbar);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherCrearPisos.launch(new Intent(MainActivity.this, AddPisoActivity.class));
            }
        });
    }

    private void inicializaLaunchers() {
        launcherCrearPisos = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK){
                    if (result.getData() != null && result.getData().getExtras() != null){
                        Piso piso = (Piso) result.getData().getExtras().getSerializable("PISO");
                        pisos.add(piso);
                        pintarElementos();
                    }
                }
            }
        });
    }

    private void pintarElementos() {
        for (Piso p:pisos) {

            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View alumnoView = inflater.inflate(R.layout.piso_model_view, null);
        }
    }
}