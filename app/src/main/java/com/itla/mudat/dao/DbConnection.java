package com.itla.mudat.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by iconlabs on 25/11/17.
 */

public class DbConnection extends SQLiteOpenHelper
{
    public static String DATABASE_NAME = "mudat.db";
    public static String LOG_T = "DbConnection";

    public DbConnection(Context context)
    {
        super(context, DATABASE_NAME, null, 9);
    }



    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SqlHelperSchema.USUARIO_TABLE);
        db.execSQL(SqlHelperSchema.ANUNCIO_TABLE);
        db.execSQL(SqlHelperSchema.CATEGORIA_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {

        db.execSQL(SqlHelperSchema.ANUNCIO_TABLE);


    }
}
