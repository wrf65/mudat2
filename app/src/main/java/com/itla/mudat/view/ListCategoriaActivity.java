package com.itla.mudat.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.itla.mudat.R;
import com.itla.mudat.dao.CategoriaDbo;
import com.itla.mudat.dao.UsuarioDbo;
import com.itla.mudat.entily.Categoria;
import com.itla.mudat.entily.Usuario;
import com.itla.mudat.view.listadapter.CategoriaListAdapter;
import com.itla.mudat.view.listadapter.UsuarioListAdapter;

import java.util.List;

public class ListCategoriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categoria);

        CategoriaDbo categoriaDbo = new CategoriaDbo(this);
        final List<Categoria> categorias = categoriaDbo.buscar();

        ListView listView =  (ListView) findViewById(R.id.lvCategoria);
        listView.setAdapter(new CategoriaListAdapter(categorias,this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent rCategoria = new Intent(ListCategoriaActivity.this, CategoriaActivity.class);
                Categoria u = (Categoria) adapterView.getItemAtPosition(i);
                rCategoria.putExtra("categoria", u);
                startActivity(rCategoria);
            }
        });

        Button btnAgregarCategoria = (Button) findViewById(R.id.btnAgregarCategoria);
        btnAgregarCategoria.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view)
            {


                Bundle parametros = new Bundle();
                //parametros.putString("nombre", txtNombre.getText().toString());
                Intent registraCategoria = new Intent(ListCategoriaActivity.this, CategoriaActivity.class);
                //listaUsuario.putExtras(parametros);
                startActivity(registraCategoria);

            }
        });

    }


}
