package com.example.androiddemo.helper

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.androiddemo.bean.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User): Long
    @Update
    fun update(user: User)
    @Query("SELECT * FROM user")
    fun getAll(): List<User>
    @Delete
    fun delete(user: User)
    @Query("SELECT * FROM user WHERE UserName LIKE :name")
    fun findByName(name: String): List<User>
}