package com.example.adeelahmed.chatapplication.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.adeelahmed.chatapplication.MainActivity
import com.example.adeelahmed.chatapplication.Message
import com.example.adeelahmed.chatapplication.R
import com.example.adeelahmed.chatapplication.UserActivity

class chatAdapter(var ctx:Context, var chatData:ArrayList<Message>) : RecyclerView.Adapter<chatAdapter.messagesView>() {
    var counter = 0


    override fun onCreateViewHolder(p0: ViewGroup, position: Int): messagesView {
        MainActivity.chatData.size
        counter+=1
        if(counter %2 ==0) {
            val personMsgView = LayoutInflater.from(ctx).inflate(R.layout.person_msgs, null)
            return messagesView(personMsgView)
        }
      else {
            val userMsgView = LayoutInflater.from(ctx).inflate(R.layout.user_msgs,null)
            return messagesView(userMsgView)
            }
    }

    override fun getItemCount(): Int {
      return  chatData.size
    }

    override fun onBindViewHolder(holder: messagesView, position: Int) {
           holder.userMessage?.text = chatData[position].msg
           holder.perosn_Name?.text = UserActivity.personName
           holder.person_msg?.text = chatData[position].msg
    }
    inner class messagesView(view:View) : RecyclerView.ViewHolder(view) {
        val userMessage: TextView? = view.findViewById(R.id.user_msg)
        val person_msg: TextView? = view.findViewById(R.id.person_message_body)
        var perosn_Name:TextView? = view.findViewById(R.id.person_chatname)

    }
}