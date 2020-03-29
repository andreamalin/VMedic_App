package com.example.vmedic.principal

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//Code reference: CodeAndroid
//https://www.youtube.com/channel/UCMvagHKkUlt3t_E4KxwQXXQ

val DATABASE_NAME = "VMedicDB"
val TABLE_NAME = "VMedic"
val COL_USERNAME = "Username"
val COL_PASSWORD = "Password"
val COL_JOB = "Job"
var COL_ORG = "Organization"

var org = ArrayList<String>()
var job = ""

class VMedicDataBase(context: Context?): SQLiteOpenHelper(context, DATABASE_NAME, null, 4) {
    //Creating table
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " +TABLE_NAME+ "(" +
                COL_USERNAME+" TEXT,"+
                COL_PASSWORD + " TEXT," +
                COL_JOB + " TEXT," +
                COL_ORG + " TEXT);"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    //Insert needed for table
    fun insert(user: User){
        addOrg(user) //Adding org info
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COL_USERNAME, user.username)
        cv.put(COL_PASSWORD, user.password)
        cv.put(COL_JOB, user.job)
        cv.put(COL_ORG, user.org)



        db.insert(TABLE_NAME,null,cv)
        db.close()
    }
    //For spinner
    private fun addOrg(user: User){
        var counter = 0

        for (item in org){
            if (item != user.org){
                counter++
            }
        }

        if (counter == org.size){
            org.add(user.org)
        }

        if (counter==0){
            org.add(0, "Seleccione su organizacion")
        }
    }

    fun getOrg(): ArrayList<String>{
        return org
    }

    fun getUser(username: String, password:String, org:String): Boolean{
        val db = this.readableDatabase
        val query = "Select * from "+ TABLE_NAME + " where " + COL_USERNAME + " = '"+username+"' and " +
                COL_PASSWORD + " = '"+password+"' and " + COL_ORG + " = '"+org+"'"

        val result = db.rawQuery(query, null)
        if(result.moveToFirst()) {
            job = result.getString(result.getColumnIndex(COL_JOB))

            return true
        }

        result.close()
        db.close()
        return false
    }

    fun getJob(): String{
        return job
    }
}