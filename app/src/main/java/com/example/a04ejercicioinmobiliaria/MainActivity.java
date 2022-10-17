package com.example.a04ejercicioinmobiliaria;

import android.content.Intent;
import android.os.Bundle;

import com.example.a04ejercicioinmobiliaria.configuraciones.Constantes;
import com.example.a04ejercicioinmobiliaria.modelos.Piso;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

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

        pisos = new ArrayList<>();
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
                        Piso piso = (Piso) result.getData().getExtras().getSerializable(Constantes.PISO);
                        pisos.add(piso);
                        muestraPisoContenido();
                    }
                }
            }
        });
    }

    private void muestraPisoContenido() {
        binding.contentMain.contenedor.removeAllViews();

        for (int i = 0; i < pisos.size(); i++) {
            Piso piso = pisos.get(i);

            View pisoView = LayoutInflater.from(MainActivity.this).inflate(R.layout.piso_model_view,null);
            TextView lbDireccion = pisoView.findViewById(R.id.lbDireccionPisoModel);
            TextView lbNumero = pisoView.findViewById(R.id.lbNumeroPisoModel);
            TextView lbCiudad = pisoView.findViewById(R.id.lbCiudadPisoModel);
            TextView lbProvincia = pisoView.findViewById(R.id.lbProvinciaPisoModel);
            RatingBar rbValoracion = pisoView.findViewById(R.id.rbValoracionPisoModel);

            lbDireccion.setText(piso.getDireccion());
            lbNumero.setText(String.valueOf(piso.getNumero()));
            lbCiudad.setText(piso.getCiudad());
            lbProvincia.setText(piso.getProvincia());
            rbValoracion.setRating(piso.getValoracion());

            binding.contentMain.contenedor.addView(pisoView);
        }
    }
}