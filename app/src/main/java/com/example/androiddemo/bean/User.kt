package com.example.androiddemo.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(var UserName: String, var UserAge: String, var UserQQ: Int){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
