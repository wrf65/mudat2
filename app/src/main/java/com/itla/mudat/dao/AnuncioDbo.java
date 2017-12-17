package com.itla.mudat.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itla.mudat.entily.Anuncio;
import com.itla.mudat.entily.Categoria;
import com.itla.mudat.entily.Usuario;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ITregional 3 on 05/12/2017.
 */

public class AnuncioDbo {

    private DbConnection con;

    final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public AnuncioDbo(Context context) {
        con = new DbConnection(context);
    }

    public void crear(Anuncio anuncio) {
        SQLiteDatabase db = con.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("usuario", anuncio.getUsuario().getId());
        cv.put("categoria", anuncio.getCategoria().getId());
       // cv.put("fecha", df.format(anuncio.getFecha()));
        cv.put("condicion", anuncio.getCondicion());
        cv.put("precio", anuncio.getPrecio());
        cv.put("titulo", anuncio.getTitulo());
        cv.put("ubicacion", anuncio.getUbicacion());
        cv.put("descripcion", anuncio.getDescripcion());

        if (anuncio.getId() == 0) {
            Long id = db.insert("anuncio", null, cv);
        } else {
            db.update("anuncio", cv, "id = " + anuncio.getId(), null);
        }

        db.close();
    }

    public List<Anuncio> buscar() {
        List<Anuncio> anuncios = new ArrayList<>();
        SQLiteDatabase db = con.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT a.*, u.nombre as u_nombre, c.descripcion " +
                "FROM anuncio a, usuario u, categoria c " +
                "WHERE a.usuario = u.id AND a.categoria = c.id", null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Anuncio a = new Anuncio();
            Usuario usuario = new Usuario();
            Categoria categoria = new Categoria();

            usuario.setId(cursor.getInt(cursor.getColumnIndex("usuario")));
            usuario.setNombre(cursor.getString(cursor.getColumnIndex("u_nombre")));
            categoria.setId(cursor.getInt(cursor.getColumnIndex("categoria")));

            a.setId(cursor.getInt(cursor.getColumnIndex("id")));
            a.setUsuario(usuario);
            a.setCategoria(categoria);
            a.setCondicion(cursor.getString(cursor.getColumnIndex("condicion")));
            a.setPrecio(cursor.getInt(cursor.getColumnIndex("precio")));
            a.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            a.setUbicacion(cursor.getString(cursor.getColumnIndex("ubicacion")));
            a.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
            cursor.moveToNext();
            anuncios.add(a);
        }
        cursor.close();
        db.close();

        return anuncios;
    }
}
