package com.itla.mudat.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.itla.mudat.R;
import com.itla.mudat.dao.UsuarioDbo;
import com.itla.mudat.entily.Usuario;

public class LoginActivity extends AppCompatActivity {
    UsuarioDbo usuarioDbo;

    public static Usuario logueado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuarioDbo = new UsuarioDbo(this);

        Button btnGuardar = (Button) findViewById(R.id.btnLogin);
        btnGuardar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view)
            {
                EditText etNombre = (EditText) findViewById(R.id.etUsuariologin);
                EditText etClave = (EditText) findViewById(R.id.etClaveLogin);
                Usuario u = new Usuario();
                u = usuarioDbo.validarLogin(etNombre.getText().toString(), etClave.getText().toString());

                if (u.getId()>0)
                {
                    logueado = u;
                    Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(mainActivity);
                }
                else{
                    logueado = null;
                }
            }
        });

        Button btnAgregarUsuarios = (Button) findViewById(R.id.btnRegistrate);
        btnAgregarUsuarios.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view)
            {


                Intent registraUsuario = new Intent(LoginActivity.this, UsuarioActivity.class);

                startActivity(registraUsuario);

            }
        });

    }
}
