package com.itla.mudat.dao;

/**

 */

public class SqlHelperSchema
{
    public static final String USUARIO_TABLE = "CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombre TEXT," +
            "tipoUsuario TEXT," +
            "identificacion TEXT," +
            "email TEXT," +
            "telefono TEXT," +
            "clave TEXT," +
            "estatus TEXT)";

    public static final String ANUNCIO_TABLE = "CREATE TABLE anuncio (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "categoria TEXT," +
            "usuario TEXT," +
            "date INTEGER," +
            "condicion INTEGER," +
            "precio DOUBLE," +
            "titulo TEXT," +
            "ubicacion TEXT," +
            "descripcion TEXT" +
            ")";

    public static final String CATEGORIA_TABLE = "CREATE TABLE categoria (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "descripcion TEXT," +
            "nombre TEXT" +
            ")";


    public static final String DROP_TABLE = "DROP TABLE anuncio";


}
