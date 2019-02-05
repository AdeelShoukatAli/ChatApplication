package com.example.adeelahmed.chatapplication.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adeelahmed.chatapplication.Adapters.RecycleViewAdapter
import com.example.adeelahmed.chatapplication.MainActivity

import android.R
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.example.adeelahmed.chatapplication.Adapters.chatAdapter
import com.example.adeelahmed.chatapplication.ChatActivity
import com.example.adeelahmed.chatapplication.MainActivity.Companion.userList
import com.example.adeelahmed.chatapplication.Message
import com.example.adeelahmed.chatapplication.UserActivity
import com.google.gson.Gson


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class chatsFragment : Fragment() {
    lateinit var myAdapter: RecycleViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(com.example.adeelahmed.chatapplication.R.layout.fragment_chats, container, false)
        var recylerView = view.findViewById<RecyclerView>(com.example.adeelahmed.chatapplication.R.id.recyler_view)
        var cAdapter = chatAdapter(activity!!,MainActivity.chatData)
        myAdapter = RecycleViewAdapter(activity!!,MainActivity.userList, {it, position ->
                MainActivity.chatData.clear()
                val sharedPresfrences = activity?.getSharedPreferences("App",0)
                var gson = Gson()
            var userName = userList[position].userName
            UserActivity.personName = userName
                val chatStringSet = sharedPresfrences?.getStringSet(UserActivity.userName+UserActivity.personName,null)
                if(chatStringSet!= null) {
                    chatStringSet.forEach {
                        val oldMsgs = gson.fromJson(it, Message::class.java)
                        MainActivity.chatData.add(oldMsgs)
                        cAdapter.notifyDataSetChanged()
                    }
                }
                var intent = Intent(activity, ChatActivity::class.java)
                ContextCompat.startActivity(activity!!, intent, null)
                 }

        )
        recylerView.layoutManager = LinearLayoutManager(activity)
        recylerView.adapter = myAdapter
        myAdapter.notifyDataSetChanged()

    return view
    }


}
