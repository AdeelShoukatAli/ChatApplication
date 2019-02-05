package com.example.adeelahmed.chatapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.widget.*
import com.example.adeelahmed.chatapplication.Adapters.chatAdapter
import com.example.adeelahmed.chatapplication.MainActivity.Companion.chatData
import com.example.adeelahmed.chatapplication.R
import com.google.gson.Gson
import java.util.ArrayList

class ChatActivity : AppCompatActivity() {
    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        //getting views
        val chatBox: EditText = findViewById(R.id.chat_et_chatActivity)
        val chatRecyclerView = findViewById<RecyclerView>(R.id.recyler_chat_view)
        val sendBtn: ImageButton = findViewById(R.id.send_btn_chatActivity)
         val chatViewAdapter = chatAdapter(this@ChatActivity, chatData)
        chatRecyclerView.layoutManager = LinearLayoutManager(this@ChatActivity)
        chatRecyclerView.adapter = chatViewAdapter
        chatViewAdapter.notifyDataSetChanged()
        setTitle(UserActivity.personName)

//        chatBox.setOnClickListener {
//            Toast.makeText(this@ChatActivity, "Clicked", Toast.LENGTH_SHORT).show()
//            sendBtn.isEnabled = chatBox.text.isNotEmpty()
//        }
        // setTitle(this.intent?.extras?.getString("user"))
        //Toast.makeText(this@ChatActivity,intent?.extras?.getString("user"),Toast.LENGTH_SHORT).show()
        sendBtn.setOnClickListener {
            setData(chatBox,chatData,chatViewAdapter)
            chatViewAdapter.notifyDataSetChanged()
            chatBox.setText("")
        }
    }


    fun setData(chatBox:EditText, chatData: ArrayList<Message>, chatViewAdapter: chatAdapter) {
        val sharedPrefrence = this@ChatActivity.getSharedPreferences("App", 0)
        val gson = Gson()
        val editer = sharedPrefrence?.edit()
        val chatStringset = sharedPrefrence.getStringSet(UserActivity.userName + UserActivity.personName, null)
        val UserMsgs = gson.toJson(Message(UserActivity.userName, chatBox.text.toString()))

//            chatData.add(Message("",chatBox.text.toString()))
//            chatViewAdapter.notifyDataSetChanged()
        if (chatStringset !=null) {
//            chatStringset.forEach {
//
////                for (i in chatData.iterator()) {
////                    if(userMessage == i) {
////                        Toast.makeText(this@ChatActivity,"Exist",Toast.LENGTH_SHORT).show()
////                        continue
////                    }
//                    chatData.add(Message("",chatBox.text.toString()))
//                    chatViewAdapter.notifyDataSetChanged()
//                }
                chatStringset.add(UserMsgs)
            val userMessage = gson.fromJson(UserMsgs, Message::class.java)
            chatData.add(userMessage)
            chatViewAdapter.notifyDataSetChanged()
                editer?.putStringSet(UserActivity.userName + UserActivity.personName, chatStringset)
                editer?.apply()
//                Toast.makeText(this@ChatActivity,"If",Toast.LENGTH_SHORT).show()
        } else {
           // Toast.makeText(this@ChatActivity, "Else", Toast.LENGTH_SHORT).show()
            var UserhashSet = HashSet<String>()
            //   val PersonhashSet = HashSet<String>()
            UserhashSet.add(UserMsgs)
            val userMessage = gson.fromJson(UserMsgs, Message::class.java)
            chatData.add(userMessage)
            //  PersonhashSet.add(personMsgs)
            editer?.putStringSet(UserActivity.userName + UserActivity.personName, UserhashSet)
            //  editer?.putStringSet(UserActivity.userName+UserActivity.personName,PersonhashSet)
            editer?.commit().apply {}
            chatViewAdapter.notifyDataSetChanged()
            // chatViewAdapter.notifyDataSetChanged()
        }
    }


//        fun getData(chatData:ArrayList<Message>, chatViewAdapter:chatAdapter) {
//            val sharedPrefrence = this@ChatActivity.getSharedPreferences("App", 0)
//            val gson = Gson()
//            val chatStringset = sharedPrefrence.getStringSet(UserActivity.userName + UserActivity.personName, null)
//            //          chatData.add(Message("",chatBox.text.toString()))
////            chatViewAdapter.notifyDataSetChanged()
//            if (chatStringset != null) {
////                Toast.makeText(this@ChatActivity,"If",Toast.LENGTH_SHORT).show()
//                chatStringset.forEach {
//                    val usetMessage = gson.fromJson(it, Message::class.java)
//                    chatData.add(usetMessage)
//                    chatViewAdapter.notifyDataSetChanged()
//               }
//            }
//        }

}
