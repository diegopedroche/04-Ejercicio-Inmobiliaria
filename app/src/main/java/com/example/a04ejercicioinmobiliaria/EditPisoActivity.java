package com.example.a04ejercicioinmobiliaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a04ejercicioinmobiliaria.configuraciones.Constantes;
import com.example.a04ejercicioinmobiliaria.databinding.ActivityAddPisoBinding;
import com.example.a04ejercicioinmobiliaria.databinding.ActivityEditPisoBinding;
import com.example.a04ejercicioinmobiliaria.modelos.Piso;

public class EditPisoActivity extends AppCompatActivity {

    private ActivityEditPisoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditPisoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            Piso piso = (Piso) bundle.getSerializable(Constantes.PISO);

            binding.txtDireccionEdit.setText(piso.getDireccion());
            binding.txtNumeroEdit.setText(String.valueOf(piso.getNumero()));
            binding.txtCiudadEdit.setText(piso.getCiudad());
            binding.txtCPEdit.setText(String.valueOf(piso.getCp()));
            binding.txtProvinciaEdit.setText(piso.getProvincia());
            binding.wgRatingEdit.setRating(piso.getValoracion());
        }



        binding.btnCancelarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnEditarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Piso pisoEditado = editarPiso();
                if (pisoEditado != null){
                    Intent intent= new Intent();
                    Bundle bundle = new Bundle();
                    int posicion = bundle.getInt(Constantes.POSICION);
                    bundle.putInt(Constantes.POSICION,posicion);
                    bundle.putSerializable(Constantes.PISO,pisoEditado);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        binding.btnEliminarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicion = bundle.getInt(Constantes.POSICION);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(Constantes.POSICION,posicion);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
    private Piso editarPiso(){
        if(binding.txtDireccionEdit.getText().toString().isEmpty() || binding.txtNumeroEdit.getText().toString().isEmpty() ||
                binding.txtCiudadEdit.getText().toString().isEmpty() || binding.txtProvinciaEdit.getText().toString().isEmpty() ||
                binding.txtCPEdit.getText().toString().isEmpty()) {
            return null;
        }

        String direccion = binding.txtDireccionEdit.getText().toString();
        int numero = Integer.parseInt(binding.txtNumeroEdit.getText().toString());
        String ciudad = binding.txtCiudadEdit.getText().toString();
        String provincia = binding.txtProvinciaEdit.getText().toString();
        String CP = binding.txtCPEdit.getText().toString();
        float rating = binding.wgRatingEdit.getRating();

        return new Piso(direccion, numero, ciudad, provincia, CP, rating);
    }
}