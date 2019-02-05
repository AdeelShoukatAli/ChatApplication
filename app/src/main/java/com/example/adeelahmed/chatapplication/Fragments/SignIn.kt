package com.example.adeelahmed.chatapplication.Fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.adeelahmed.chatapplication.MainActivity

import com.example.adeelahmed.chatapplication.R
import com.example.adeelahmed.chatapplication.UserActivity
import com.example.adeelahmed.chatapplication.Users
import com.google.gson.Gson

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SignIn : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        val userEmail:EditText = view.findViewById(R.id.userEmail_et_signin)
        val userPassword:EditText = view.findViewById(R.id.password_et_signin)
        val signinBtn:Button = view.findViewById(R.id.btn_signin)
        signinBtn.setOnClickListener{
            if(userEmail.text.trim().toString().isNotBlank()) {
                if(userPassword.text.trim().toString().isNotBlank()) {
                    signIn(userEmail.text.trim().toString(), userPassword.text.trim().toString())
                    userEmail.setText("")
                    userPassword.setText("")
                }else {userPassword.setError("Invalid Input!")}

            } else {userEmail.setError("Invalid Input!")}
        }
    }

    private fun signIn(userEmail: String, userPassword: String) {
        val sharedPrefrencess = activity?.getSharedPreferences("App",0)
        val gson = Gson()
        val userStringSet = sharedPrefrencess?.getStringSet("userss",null)
        if(userStringSet != null) {
            MainActivity.userList.clear()
            userStringSet.forEach {
                val user = gson.fromJson(it, Users::class.java)
                Log.d("Users", user.toString())
                MainActivity.userList.add(user)
                    if (user.userEmail.equals(userEmail) && user.userPassword.equals(userPassword)) {
                        MainActivity.userList.remove(user)
                        UserActivity.userName = user.userName
                        Toast.makeText(activity, "Sign in Success", Toast.LENGTH_SHORT).show()

                        val intent = Intent(activity, UserActivity::class.java)
//                        intent.putExtra("users", user.userName)

                        startActivity(intent)
                    activity?.finish()
                        return
                    }

            }
                Toast.makeText(activity, "No user credential found", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity,"No user found",Toast.LENGTH_SHORT).show()
        }
    }
}
