package com.example.a04ejercicioinmobiliaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a04ejercicioinmobiliaria.configuraciones.Constantes;
import com.example.a04ejercicioinmobiliaria.databinding.ActivityAddPisoBinding;
import com.example.a04ejercicioinmobiliaria.modelos.Piso;

public class AddPisoActivity extends AppCompatActivity {

    private ActivityAddPisoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPisoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Piso piso = createPiso();
                if(piso != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constantes.PISO, piso);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(AddPisoActivity.this,"FALTAN DATOS",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Piso createPiso(){
        if(binding.txtDireccionAdd.getText().toString().isEmpty() || binding.txtNumeroAdd.getText().toString().isEmpty() ||
                binding.txtCiudadAdd.getText().toString().isEmpty() || binding.txtProvinciaAdd.getText().toString().isEmpty() ||
                binding.txtCPAdd.getText().toString().isEmpty()) {
            return null;
        }

        String direccion = binding.txtDireccionAdd.getText().toString();
        int numero = Integer.parseInt(binding.txtNumeroAdd.getText().toString());
        String ciudad = binding.txtCiudadAdd.getText().toString();
        String provincia = binding.txtProvinciaAdd.getText().toString();
        String CP = binding.txtCPAdd.getText().toString();
        float rating = binding.wgRating.getRating();

        return new Piso(direccion, numero, ciudad, provincia, CP, rating);
    }
}