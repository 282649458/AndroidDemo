package com.example.androiddemo.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddemo.R
import com.example.androiddemo.bean.User

class UserAdapter: ListAdapter<User, UserAdapter.UserViewHolder>(object :
    DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id== newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(View.inflate(parent.context, R.layout.rv_user, null))
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val room_name = itemView.findViewById<TextView>(R.id.tv_name)
        private val room_age = itemView.findViewById<TextView>(R.id.tv_age)
        private val room_qq = itemView.findViewById<TextView>(R.id.tv_qq)
        init {
            itemClick()
        }
        fun itemClick() {
//            itemView.setOnClickListener {
//
//            }
        }

        fun bind(user: User) {
            room_name.text = user.UserName
            room_age.text = user.UserAge
            room_qq.text = user.UserQQ.toString()
        }
    }
}