package com.itla.mudat.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.itla.mudat.R;
import com.itla.mudat.dao.AnuncioDbo;
import com.itla.mudat.dao.CategoriaDbo;
import com.itla.mudat.entily.Anuncio;
import com.itla.mudat.entily.Categoria;
import com.itla.mudat.view.listadapter.CategoriaListAdapter;

public class RegistroAnuncioActivity extends AppCompatActivity {


    AnuncioDbo anuncioDbo;
    CategoriaDbo categoriaDbo;
    EditText etTitulo;
    EditText etDescripcion;
    EditText etPrecio;
    Spinner etCategoria;
    EditText etUbicacion;
    Anuncio anuncio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_anuncio);
        anuncioDbo = new AnuncioDbo(this);
        categoriaDbo = new CategoriaDbo(this);

        etTitulo = (EditText) findViewById(R.id.etTituloAnuncio);
        etDescripcion = (EditText) findViewById(R.id.etDescripcionAnuncio);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        etCategoria = (Spinner) findViewById(R.id.etCategoriaAnuncio);
        etUbicacion = (EditText) findViewById(R.id.etUbicacion);
        etCategoria.setAdapter(new CategoriaListAdapter(categoriaDbo.buscar(),this));



        llenaAnuncio();
        anuncioDbo = new AnuncioDbo(this);
        Button btnguardaranuncio = (Button) findViewById(R.id.btnGuardarAnuncio);
        btnguardaranuncio.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                if (anuncio==null){
                    anuncio = new Anuncio();
                }
                anuncio.setTitulo(etTitulo.getText().toString());
                anuncio.setDescripcion(etDescripcion.toString());
                anuncio.setUbicacion(etUbicacion.getText().toString());
                anuncio.setPrecio(Double.parseDouble(etPrecio.getText().toString()));
                anuncio.setCategoria ((Categoria)etCategoria.getSelectedItem());
                anuncio.setUsuario(LoginActivity.logueado);
                anuncioDbo.crear(anuncio);
            }
        });

    }

    private void llenaAnuncio()
    {
        Bundle parametros = getIntent().getExtras();
        if(parametros != null && parametros.containsKey("anuncio"))
        {

            Anuncio anuncio = new Anuncio();
            anuncio = (Anuncio) getIntent().getExtras().getSerializable("anuncio");
            etTitulo.setText(anuncio.getTitulo());
            etDescripcion.setText(anuncio.getDescripcion());
            etUbicacion.setText(anuncio.getUbicacion());
            etPrecio.setText(String.valueOf(anuncio.getPrecio()));
            etCategoria.setSelection(UsuarioActivity.obtenerPosicion(etCategoria, anuncio.getCategoria().getNombre()));


        }
    }
}
