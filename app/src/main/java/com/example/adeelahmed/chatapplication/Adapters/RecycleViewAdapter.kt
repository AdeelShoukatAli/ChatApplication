package com.example.adeelahmed.chatapplication.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.adeelahmed.chatapplication.*
import com.google.gson.Gson

class RecycleViewAdapter(var ctx:Context,var userList:ArrayList<Users>, var viewClick: (view:View,position:Int) -> Unit) : RecyclerView.Adapter<RecycleViewAdapter.customViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, position: Int): customViewHolder {
       val view = LayoutInflater.from(ctx).inflate(R.layout.card_row,null)

        return customViewHolder(view)
    }
    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: customViewHolder, position: Int) {
    var userDetail = userList[position]
        holder.userName.text = userDetail.userName
        holder.userImage.setOnClickListener {
            viewClick(it, position)
        }
    }

    inner class customViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var userName = view.findViewById<TextView>(R.id.userName_tv_cardRow)
        var userImage = view.findViewById<ImageView>(R.id.user_img)
    }
}