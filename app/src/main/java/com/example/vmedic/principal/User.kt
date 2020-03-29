package com.example.vmedic.principal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity class for vmedic table
@Entity(tableName = "v-medic_table")
class User {
    //Allows android to manage IDs
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "username")
    var username: String = "" //Poll id

    @ColumnInfo(name = "password")
    var password: String = "" //Question id

    @ColumnInfo(name = "job")
    var job: String = "" //Answer for strings

    @ColumnInfo(name = "org")
    var org: String = ""//Answer for rating/numbers


    //For new user
    constructor(username: String, password: String, job: String, org: String) {
        this.username = username
        this.password = password
        this.job = job
        this.org = org
    }
}