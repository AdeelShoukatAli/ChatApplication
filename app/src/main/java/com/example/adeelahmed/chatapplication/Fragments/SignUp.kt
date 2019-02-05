package com.example.adeelahmed.chatapplication.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.adeelahmed.chatapplication.MainActivity

import com.example.adeelahmed.chatapplication.R
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
class SignUp : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        var userName:EditText = view.findViewById(R.id.userName_et_signup)
        var userEmail:EditText = view.findViewById(R.id.userEmail_et_signup)
        var userPassword:EditText = view.findViewById(R.id.password_et_signup)
        var signUpbtn: Button = view.findViewById(R.id.btn_signUp)
        signUpbtn.setOnClickListener{
            if(userName.text.isNotEmpty()) {
                if(userEmail.text.isNotEmpty()) {
                    if(userPassword.text.isNotEmpty()) {
                        if(userPassword.text.length>=5) {
                            Signup(Users(userName.text.toString(),userEmail.text.toString(),userPassword.text.toString()))
                            userName.setText("")
                            userEmail.setText("")
                            userPassword.setText("")
                        }else {userPassword.setError("Password length can not be less than 5")}
                    } else {userPassword.setError("Please input password")}
                }
            } else {userName.setError("Please input user name")}
        }
        return view
    }

    private fun Signup(user:Users) {
        var sharedPreferences = activity?.getSharedPreferences("App",0)
        val gson = Gson()
        var editer = sharedPreferences?.edit()
        var userStringObj = gson.toJson(user)
        var userStringSet = sharedPreferences?.getStringSet("userss",null)
           if(userStringSet != null) {
            userStringSet.forEach {
                var userObj = gson.fromJson(it, Users::class.java)
                if (user.userEmail.equals(userObj.userEmail)) {
                    Toast.makeText(activity, "User Exist", Toast.LENGTH_SHORT).show()
                    return
                }
            }
                userStringSet.add(userStringObj)

                editer?.putStringSet("userss",userStringSet)
                editer?.apply {}
                Toast.makeText(activity, "Sign Up Successful, Please log in to continue",Toast.LENGTH_SHORT).show()

        } else {
            val hashSet = HashSet<String>()
            hashSet.add(userStringObj)
            editer?.putStringSet("userss",hashSet)
            editer?.commit().apply {}

               Toast.makeText(activity, "Sign Up Successful, Please log in to continue",Toast.LENGTH_SHORT).show()
        }
//        val sharedPreferences = activity?.getSharedPreferences("App", 0)
//        val gson =Gson()
//        val editer = sharedPreferences?.edit()
//        val userStringObj = gson.toJson(user)
//
//        val userStringSet = sharedPreferences?.getStringSet("users", null)
//        if(userStringSet!=null){
//            userStringSet.forEach {
//                val userObj = gson.fromJson(it, Users::class.java)
//                if(user.userEmail.equals(userObj.userEmail)){
//                    Toast.makeText(activity, "User Exist",Toast.LENGTH_SHORT).show()
//                    return
//                }
//            }
//            userStringSet.add(userStringObj)
//            editer?.putStringSet("users",userStringSet )
//            editer?.apply()
//            Toast.makeText(activity, "User added Succesfully",Toast.LENGTH_SHORT).show()
//
//        }else{
//            val hashSet = HashSet<String>()
//            hashSet.add(userStringObj)
//            editer?.putStringSet("users",hashSet )
//            editer?.apply()
//            Toast.makeText(activity, "User Added Succesfully",Toast.LENGTH_SHORT).show()
//
//        }

    }


}
