package com.example.adeelahmed.chatapplication

import java.io.Serializable

data class Users (var userName:String, var userEmail:String, var userPassword:String) : Serializable {

    override fun toString(): String {
        return "Users(userName='$userName', userEmail='$userEmail', userPassword='$userPassword')"
    }
}