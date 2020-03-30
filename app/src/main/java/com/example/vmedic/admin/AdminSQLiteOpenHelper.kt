package com.example.vmedic.admin

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import com.example.vmedic.admin.Admin

//Code reference: https://www.tutorialesprogramacionya.com/kotlinparaandroidya/detalleconcepto.php?

class AdminSQLiteOpenHelper (
    context: Context, name: String, factory: CursorFactory?,
    version: Int): SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table medicamentos(Nombre String primary key, Presentacion , Descripcion)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}
