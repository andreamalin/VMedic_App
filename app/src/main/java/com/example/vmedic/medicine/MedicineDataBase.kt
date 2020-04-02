package com.example.vmedic.medicine

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.vmedic.doctor.recipeList

//Code reference: CodeAndroid
//https://www.youtube.com/channel/UCMvagHKkUlt3t_E4KxwQXXQ

val DATABASE_NAME = "MedicineDB"
val TABLE_NAME = "Medicine"
val COL_NAME = "Name"
val COL_AMOUNT = "Amount"
val COL_PRESENTATION = "Presentation"
var COL_DUE_DATE = "Due_Date"
var COL_LOCATION = "Location"
var COL_ID = "ID"

var presentation = ArrayList<String>()
var amount = 0
var dueDate = ""
var medLocation = ""

var medicineList = ArrayList<Medicine>()

class MedicineDataBase(context: Context?): SQLiteOpenHelper(context, DATABASE_NAME, null, 4) {
    //Creating table
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " +TABLE_NAME+ "(" +
                COL_NAME+" TEXT,"+
                COL_AMOUNT + " TEXT," +
                COL_PRESENTATION + " TEXT," +
                COL_DUE_DATE + " TEXT," +
                COL_LOCATION + ");"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    //Insert needed for table
    fun insert(medicine : Medicine){
        medicineList.add(medicine) //Adding to list for card views
        addPresentation (medicine) //Adding org info
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COL_NAME, medicine.name)
        cv.put(COL_AMOUNT, medicine.amount)
        cv.put(COL_PRESENTATION, medicine.presentation)
        cv.put(COL_DUE_DATE, medicine.dueDate)
        cv.put(COL_LOCATION, medicine.location)



        db.insert(TABLE_NAME,null,cv)
        db.close()
    }
    //For spinner
    private fun addPresentation(medicine : Medicine){
        var counter = 0

        for (item in presentation){
            if (item != medicine.presentation){
                counter++
            }
        }

        if (counter == presentation.size){
            presentation.add(medicine.presentation)
        }

        if (counter==0){
           presentation.add(0, "Presentación")
        }
    }

    fun getPresentation(): ArrayList<String>{
        return presentation
    }

    fun getMedicine(name: String, presentation :String): Boolean{
        val db = this.readableDatabase
        val query = "Select * from "+ TABLE_NAME + " where " + COL_NAME + " = '"+name+"' and " +
                COL_PRESENTATION + " = '"+presentation+"'"

        val result = db.rawQuery(query, null)
        if(result.moveToFirst()) {
            amount = result.getInt(result.getColumnIndex(COL_AMOUNT))
            dueDate = result.getString(result.getColumnIndex(COL_DUE_DATE))
            medLocation = result.getString(result.getColumnIndex(COL_LOCATION))
            return true
        }

        result.close()
        db.close()
        return false
    }
    fun subtractAmount(name: String, presentation :String, amountReq:Int): Boolean {

        val dbRead = this.readableDatabase
        val query =
            "Select * from " + TABLE_NAME + " where " + COL_NAME + " = '" + name + "' and " +
                    COL_PRESENTATION + " = '" + presentation + "'"

        val result = dbRead.rawQuery(query, null)
        if (result.moveToFirst()) {
            val amount = result.getInt(result.getColumnIndex(COL_AMOUNT))
            val dueDate = result.getString(result.getColumnIndex(COL_DUE_DATE))
            val medLocation = result.getString(result.getColumnIndex(COL_LOCATION))
            val ID = result.getInt(result.getColumnIndex(COL_ID))

            val dbWrite = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(COL_ID, ID)
            contentValues.put(COL_NAME, name)
            contentValues.put(COL_AMOUNT, amount - amountReq)
            contentValues.put(COL_LOCATION, medLocation)
            contentValues.put(COL_DUE_DATE, dueDate)
            contentValues.put(COL_PRESENTATION, presentation)
            dbWrite.update(TABLE_NAME, contentValues, "Amount = ?", null)
            dbWrite.close()
            return true

        } else
            dbRead.close()

        return false
    }

    fun getAmount(): Int{
        return amount
    }
    fun getDueDate(): String{
        return dueDate
    }
    fun getMedLocation(): String{
        return medLocation
    }

    fun getMedicineList(): ArrayList<Medicine>{
        return medicineList
    }

    fun cleanMedicineList(){
        medicineList.clear()
    }
}