package com.example.adeelahmed.chatapplication.Fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.adeelahmed.chatapplication.*
import com.example.adeelahmed.chatapplication.Adapters.chatAdapter
import com.example.adeelahmed.chatapplication.Adapters.userViewAdapter

import com.google.gson.Gson

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class users_Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_users, container, false)
        val userList: RecyclerView = view.findViewById(R.id.users_recyclerView)
        val cAdapter = chatAdapter(activity!!,MainActivity.chatData)
        var listAdapter = userViewAdapter(activity!!,{it,position->
            var userName = MainActivity.userList[position].userName
            UserActivity.personName = userName
            MainActivity.chatData.clear()
            val sharedPresfrences = activity?.getSharedPreferences("App",0)
            var gson = Gson()
            val chatStringSet = sharedPresfrences?.getStringSet(UserActivity.userName+ UserActivity.personName,null)
            if(chatStringSet!= null) {
                chatStringSet.forEach {
                    val oldMsgs = gson.fromJson(it, Message::class.java)
                    MainActivity.chatData.add(oldMsgs)
                    cAdapter.notifyDataSetChanged()
                }
            }

            var intent = Intent(activity, ChatActivity::class.java)
            startActivity(intent)


        },MainActivity.userList)
        userList.adapter = listAdapter
        userList.layoutManager = LinearLayoutManager(activity)
        listAdapter.notifyDataSetChanged()
//        val userNames_list:ArrayList<String> = ArrayList()
//        for(i in MainActivity.userList) {
//            userNames_list.add(i.userName)
//        }
//       val arrayAdapter = ArrayAdapter(activity!!,R.layout.user_list_layout,userNames_list)
//        var view1 = LayoutInflater.from(activity).inflate(R.layout.user_list_layout,null)
//        userList.adapter = arrayAdapter
//        userList.setOnItemClickListener{ adapterView: AdapterView<*>, view1: View, position: Int, l: Long ->
//        Toast.makeText(activity,position.toString(),Toast.LENGTH_SHORT).show()
//        }


        return view
    }


}
