package com.example.adeelahmed.chatapplication

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.adeelahmed.chatapplication.Adapters.ViewPager_user
import com.example.adeelahmed.chatapplication.Fragments.chatsFragment

class UserActivity : AppCompatActivity() {

    companion object {
        lateinit var userName:String
        var personName:String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        var viewPager = findViewById<ViewPager>(R.id.view_pager_chat)
        var tabLayout = findViewById<TabLayout>(R.id.tab_layout)
//        userName = intent.getStringExtra("user")
        setTitle("Welcome, "+userName)
        tabLayout.setupWithViewPager(viewPager)
        var pagerAdapter = ViewPager_user(this@UserActivity,supportFragmentManager)
        viewPager.adapter = pagerAdapter
        pagerAdapter.notifyDataSetChanged()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this@UserActivity)
        inflater.inflate(R.menu.list_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
       return when(item?.itemId) {
            R.id.log_out -> {
                logout()
                true
            } else -> false
        }

    }

    private fun logout() {
        val alertDialog = AlertDialog.Builder(this@UserActivity)
        alertDialog.create()
        alertDialog.setTitle("Log Out")
        alertDialog.setMessage("Are you sure want to log out?")
        alertDialog.setNegativeButton("Yes", object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                UserActivity.userName = ""
                UserActivity.personName = ""
                val intent = Intent(this@UserActivity,MainActivity()::class.java)
                startActivity(intent)
                Toast.makeText(this@UserActivity, "Logged out",Toast.LENGTH_SHORT).show()
                this@UserActivity.finish()

            }

        })
        alertDialog.setPositiveButton("No", object :DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {

            }
        })
        alertDialog.show()

    }

}
