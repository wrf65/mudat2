package com.itla.mudat.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.itla.mudat.R;
import com.itla.mudat.dao.CategoriaDbo;
import com.itla.mudat.entily.Categoria;

/**
 * Created by ITregional 3 on 14/12/2017.
 */

public class CategoriaActivity extends AppCompatActivity{

    private static final String LOG_TAG = "CategoriaActivity";
    CategoriaDbo categoriaDbo;
    EditText etNombre ;
    EditText etDescripcion;
    Categoria categoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        categoriaDbo = new CategoriaDbo(this);

        etNombre = (EditText) findViewById(R.id.etNombreCategoria);
        etDescripcion = (EditText) findViewById(R.id.etDescripcionCategoria);

        llenaCategoria();


        Button btnGuardarCategoria = (Button) findViewById(R.id.btnGuardarCategoria);
        btnGuardarCategoria.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view)
            {

                if (categoria==null){
                    categoria = new Categoria();
                }
                categoria.setNombre(etNombre.getText().toString());
                categoria.setDescripcion(etDescripcion.getText().toString());

                categoriaDbo.crear(categoria);

            }
        });



    }

    private void llenaCategoria()
    {
        Bundle parametros = getIntent().getExtras();
        if(parametros != null && parametros.containsKey("categoria"))
        {

            Categoria categoria = new Categoria();
            categoria = (Categoria) getIntent().getExtras().getSerializable("categoria");
            etNombre.setText(categoria.getNombre());
            etDescripcion.setText(categoria.getDescripcion());

        }
    }
}
