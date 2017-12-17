package com.itla.mudat.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.itla.mudat.R;
import com.itla.mudat.dao.AnuncioDbo;
import com.itla.mudat.dao.CategoriaDbo;
import com.itla.mudat.entily.Anuncio;
import com.itla.mudat.entily.Categoria;
import com.itla.mudat.entily.Usuario;
import com.itla.mudat.view.listadapter.AnuncioListAdapter;
import com.itla.mudat.view.listadapter.CategoriaListAdapter;

import java.io.Serializable;
import java.util.List;


public class AnunciosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);

        AnuncioDbo anuncioDbo = new AnuncioDbo(this);
        final List<Anuncio> anuncios = anuncioDbo.buscar();

        ListView listView =  (ListView) findViewById(R.id.lvAnuncios);
        listView.setAdapter(new AnuncioListAdapter(anuncios,this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent rAnuncio = new Intent(AnunciosActivity.this, AnunciosActivity.class);
                Anuncio u = (Anuncio) adapterView.getItemAtPosition(i);
                rAnuncio.putExtra("anuncios", u);
                startActivity(rAnuncio);
            }
        });

        Button btnAgregarAnuncio = (Button) findViewById(R.id.btnAgregarAnuncio);
        btnAgregarAnuncio.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view)
            {
                Intent registraAnuncio = new Intent(AnunciosActivity.this, RegistroAnuncioActivity.class);
                startActivity(registraAnuncio);

            }
        });



    }
}
