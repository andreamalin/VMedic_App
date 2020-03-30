package com.example.vmedic.doctor;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Entity class for vmedic table
@Entity(tableName = "doctor_recipe_table")
class Recipe {
//Allows android to manage IDs
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "medicine")
    var medicine: String = "" //Medicine name

    @ColumnInfo(name = "dose")
    var dose: String = "" //Dose required

    @ColumnInfo(name = "extra")
    var extra: String = "" //Extra comment

    @ColumnInfo(name = "image")
    var image: String = ""//Recipe image


    constructor(medicine: String, dose:String, extra: String, image:String){
        this.medicine = medicine
        this.dose = dose
        this.extra = extra
        this.image = image
    }

}
