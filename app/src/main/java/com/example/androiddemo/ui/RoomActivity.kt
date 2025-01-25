package com.example.androiddemo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddemo.helper.AppDataBase
import com.example.androiddemo.R
import com.example.androiddemo.adapter.UserAdapter
import com.example.androiddemo.bean.User
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.concurrent.thread

class RoomActivity : AppCompatActivity() {
    private val btn_insert by lazy { findViewById<Button>(R.id.btn_insert) }
    private val roomRv by lazy { findViewById<RecyclerView>(R.id.room_rv) }
    private val mRvAdapter : UserAdapter by lazy { UserAdapter() }
    private val RoomList:MutableList<User> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userDao= AppDataBase.getDatabase(this).userDao()
        setContentView(R.layout.activity_main)
        initClick()
        setRv()
    }

    private fun initClick() {
        btn_insert.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            val view = LayoutInflater.from(this).inflate(R.layout.room_dialog, null)
            bottomSheetDialog.setContentView(view)

            val roomName = view.findViewById<EditText>(R.id.room_name)
            val roomAge = view.findViewById<EditText>(R.id.room_age)
            val roomQQ = view.findViewById<EditText>(R.id.room_qq)
            val roomAdd = view.findViewById<Button>(R.id.create_room)

            roomAdd.setOnClickListener {
                val user = User(roomName.text.toString(), roomAge.text.toString(), roomQQ.text.toString().toInt())
                thread {
                    user.id = AppDataBase.getDatabase(this).userDao().insert(user)
                    RoomList.addAll(AppDataBase.getDatabase(this).userDao().getAll())
                    runOnUiThread {
                        Log.d("Find", "${RoomList}")
                        mRvAdapter.submitList(RoomList)
                        bottomSheetDialog.dismiss()
                        Log.d("room", "${user}")
                    }
                }
            }

            bottomSheetDialog.show()
        }
    }

    private fun setRv() {
        roomRv.adapter = mRvAdapter
        roomRv.layoutManager = LinearLayoutManager(this)
        thread {
            RoomList.addAll(AppDataBase.getDatabase(this).userDao().getAll())
            runOnUiThread {
                mRvAdapter.submitList(RoomList)
            }
        }
    }
}