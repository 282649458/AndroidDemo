package com.example.androiddemo.helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androiddemo.bean.User

@Database(entities = [User::class], version = 1)
abstract class AppDataBase :RoomDatabase(){
    abstract fun userDao(): UserDao
    companion object {
        private var instance: AppDataBase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDataBase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java, "app_database"
            )
                .build().apply {
                    instance = this
                }
        }
    }
}