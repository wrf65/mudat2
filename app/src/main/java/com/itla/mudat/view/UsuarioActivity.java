package com.itla.mudat.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import com.itla.mudat.R;
import com.itla.mudat.dao.UsuarioDbo;
import com.itla.mudat.entily.Usuario;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsuarioActivity extends AppCompatActivity {

    private static final String LOG_TAG = "UsuarioActivity";
    UsuarioDbo usuarioDbo;
    EditText etNombre ;
    Spinner spiTipoUsuario;
    EditText etTelefono ;
    EditText etIdentificacion;
    EditText etEmail;
    EditText etClave;
    EditText etconfirmacion;
    Usuario usuario;
    List<String> tipos;
    ArrayAdapter<String> adapterTipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        usuarioDbo = new UsuarioDbo(this);

        etNombre = (EditText) findViewById(R.id.etnombre);
        spiTipoUsuario = (Spinner) findViewById(R.id.ettipoUsuario);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etIdentificacion = (EditText) findViewById(R.id.etidentificacion);
        etEmail = (EditText) findViewById(R.id.etemail);
        etClave = (EditText) findViewById(R.id.etclave);
        etconfirmacion = (EditText) findViewById(R.id.etconfirmacion);


        tipos =new ArrayList<>();
        String[] tipoUsuario = {"CLIENTE","PUBLICADOR"};

        Collections.addAll(tipos, tipoUsuario);
        adapterTipo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos );
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spiTipoUsuario.setAdapter(adapterTipo);
        llenaUsuario();

        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view)
            {

                if (usuario==null){
                    usuario = new Usuario();
                }
                usuario.setNombre(etNombre.getText().toString());
                usuario.setTipoUsuario(spiTipoUsuario.getSelectedItem().toString());
                usuario.setTelefono(etTelefono.getText().toString());
                usuario.setIdentificacion(etIdentificacion.getText().toString());
                usuario.setEmail(etEmail.getText().toString());
                usuario.setClave(etClave.getText().toString());
                usuario.setEstatus("1");

                usuarioDbo.crear(usuario);


            }
        });


       Button btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view)
            {
                List<Usuario> usuarios = usuarioDbo.buscar();
                for(Usuario u : usuarios)
                {
                    Log.i("UsuarioDbo_Buscar", u.toString());
                }
            }
        });
    }

    private void llenaUsuario()
    {
        Bundle parametros = getIntent().getExtras();
        if(parametros != null && parametros.containsKey("usuario"))
        {

            usuario = (Usuario) parametros.getSerializable("usuario");
            etNombre.setText(usuario.getNombre());
            etTelefono.setText(usuario.getTelefono());
            etIdentificacion.setText(usuario.getIdentificacion());
            etEmail.setText(usuario.getEmail());
            etClave.setText(usuario.getClave());
            spiTipoUsuario.setSelection(obtenerPosicion(spiTipoUsuario, usuario.getTipoUsuario()));


        }
    }
    public static int obtenerPosicion(Spinner spinner, String tipo)
    {
        int posicion = 0;
        for (int i =0; i < spinner.getCount(); i++) {

            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(tipo)) {
                posicion = i;
            }
        }
        return posicion;
    }

}
