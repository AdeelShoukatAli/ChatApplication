package com.example.adeelahmed.chatapplication.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.adeelahmed.chatapplication.R
import com.example.adeelahmed.chatapplication.Users

class userViewAdapter(var ctx:Context,var clickView:(view:View, position:Int)->Unit, var userList:ArrayList<Users>) : RecyclerView.Adapter<userViewAdapter.customViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): customViewHolder {
        var view = LayoutInflater.from(ctx).inflate(R.layout.users_row,null)
        return customViewHolder(view)
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    override fun onBindViewHolder(holder: customViewHolder, position: Int) {
        holder.textView.text = userList[position].userName
        holder.userImg.setOnClickListener {
            clickView(it, position)
        }
    }


    inner class customViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var textView:TextView = view.findViewById(R.id.userName_tv_usersRow)
        var userImg :ImageView = view.findViewById(R.id.user_img)
    }

}