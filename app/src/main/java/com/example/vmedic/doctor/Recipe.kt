package com.example.vmedic.doctor;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.vmedic.R

//Entity class for vmedic table
@Entity(tableName = "doctor_recipe_table")
class Recipe {
//Allows android to manage IDs
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "medicine")
    var medicine: String = "" //Medicine name

    @ColumnInfo(name = "dose")
    var dose: Int = 0 //Dose required

    @ColumnInfo(name = "presentation")
    var presentation: String = "" //Presentation

    @ColumnInfo(name = "image")
    var image: String = ""//Recipe image


    constructor(medicine: String, dose:Int, presentation: String, image:String){
        this.medicine = medicine
        this.dose = dose
        this.presentation = presentation

        if (image==""){
            this.image = R.drawable.noback.toString()
        } else {
            this.image = image
        }



    }

}
