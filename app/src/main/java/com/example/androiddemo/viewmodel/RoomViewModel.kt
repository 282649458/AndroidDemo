package com.example.androiddemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddemo.bean.User

class RoomViewModel: ViewModel() {
    private val room= MutableLiveData<User>()
    val mRoom :LiveData<User>
        get() = room
}