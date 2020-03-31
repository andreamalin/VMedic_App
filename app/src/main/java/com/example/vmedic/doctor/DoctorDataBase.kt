package com.example.vmedic.doctor


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

//Code reference: CodeAndroid
//https://www.youtube.com/channel/UCMvagHKkUlt3t_E4KxwQXXQ

val DATABASE_NAME = "DoctorDB"
val TABLE_NAME = "DoctorRecipes"
val COL_MEDICINE = "Medicine"
val COL_DOSE = "Dose"
val COL_PRESENTATION = "Presentation"
var COL_IMAGE = "Image"

var recipeList = ArrayList<Recipe>()
class DoctorDataBase(context: Context?): SQLiteOpenHelper(context, DATABASE_NAME, null, 4) {
    //Creating table
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " +TABLE_NAME+ "(" +
                COL_MEDICINE+" TEXT,"+
                COL_DOSE + " TEXT," +
                COL_PRESENTATION + " TEXT," +
                COL_IMAGE + " TEXT);"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    //Insert needed for table
    fun insert(recipe: Recipe){
        val db = this.writableDatabase
        val cv = ContentValues()
        recipeList.add(recipe)
        Log.d("Se agrega", "Agrega")

        cv.put(COL_MEDICINE, recipe.medicine)
        cv.put(COL_DOSE, recipe.dose)
        cv.put(COL_PRESENTATION, recipe.presentation)
        cv.put(COL_IMAGE, recipe.image)



        db.insert(TABLE_NAME,null,cv)
        db.close()
    }

    fun getRecipeList(): ArrayList<Recipe>{
        return recipeList
    }
}