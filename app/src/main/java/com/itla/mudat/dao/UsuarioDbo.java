package com.itla.mudat.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itla.mudat.entily.Usuario;

import java.util.ArrayList;
import java.util.List;


public class UsuarioDbo {
    private DbConnection con;

    public UsuarioDbo(Context context) {
        con = new DbConnection(context);
    }

    public void crear(Usuario usuario) {
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre", usuario.getNombre());
        cv.put("clave", usuario.getClave());
        cv.put("tipoUsuario", usuario.getTipoUsuario().toString());
        cv.put("telefono", usuario.getTelefono());
        cv.put("email", usuario.getEmail());
        cv.put("identificacion", usuario.getIdentificacion());
        cv.put("estatus", usuario.getEstatus());

        if (usuario.getId() == 0) {
            Long id = db.insert("usuario", null, cv);
        } else {
            db.update("usuario", cv, "id = " + usuario.getId(), null);
        }


        db.close();
    }

    public List<Usuario> buscar() {
        List<Usuario> usuarios = new ArrayList<>();
        SQLiteDatabase db = con.getReadableDatabase();
        String columnas[] = new String[]{"id", "nombre", "tipoUsuario", "email", "clave", "telefono", "identificacion", "estatus"};
        Cursor cursor = db.query("usuario", columnas, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Usuario u = new Usuario();
            u.setId(cursor.getInt(cursor.getColumnIndex("id")));
            u.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
            u.setTipoUsuario(cursor.getString(cursor.getColumnIndex("tipoUsuario")));
            u.setTelefono(cursor.getString(cursor.getColumnIndex("telefono")));
            u.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            u.setIdentificacion(cursor.getString(cursor.getColumnIndex("identificacion")));
            u.setEstatus(cursor.getString(cursor.getColumnIndex("estatus")));
            u.setClave(cursor.getString(cursor.getColumnIndex("clave")));
            cursor.moveToNext();
            usuarios.add(u);
        }
        cursor.close();
        db.close();

        return usuarios;
    }

    public Usuario validarLogin(String nombre, String clave) {

        Usuario u = new Usuario();
        SQLiteDatabase db = con.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from usuario where clave = '" + clave + "' and nombre = '" + nombre + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            u.setId(cursor.getInt(cursor.getColumnIndex("id")));
            u.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
            u.setTipoUsuario(cursor.getString(cursor.getColumnIndex("tipoUsuario")));
            u.setTelefono(cursor.getString(cursor.getColumnIndex("telefono")));
            u.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            u.setIdentificacion(cursor.getString(cursor.getColumnIndex("identificacion")));
            u.setEstatus(cursor.getString(cursor.getColumnIndex("estatus")));
            u.setClave(cursor.getString(cursor.getColumnIndex("clave")));
            cursor.moveToNext();

        }
        cursor.close();
        db.close();

        return u;
    }

}

