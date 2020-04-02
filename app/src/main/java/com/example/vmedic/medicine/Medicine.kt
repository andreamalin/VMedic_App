package com.example.vmedic.medicine

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity class for medicine
@Entity(tableName = "medicine_stock")
class Medicine{
    //Allows android to manage IDs
    @PrimaryKey(autoGenerate = true)

    //Medicine's name
    @ColumnInfo(name = "name")
    var name: String = ""

    //Stock's amount
    @ColumnInfo(name = "amount")
    var amount: Int = 0

    //Presentación, haha idk how to say it in english
    @ColumnInfo(name = "presentation")
    var presentation: String = ""

    //Medicine's Due date
    @ColumnInfo(name = "due_date")
    var dueDate: String  = ""

    //Medicine's Due date
    @ColumnInfo(name = "location")
    var location: String  = ""


    //For new medicine
    constructor(name: String, amount: Int, presentation: String, dueDate: String, location: String) {
        this.name = name
        this.amount = amount
        this.presentation = presentation
        this.dueDate = dueDate
        this.location = location
    }
}