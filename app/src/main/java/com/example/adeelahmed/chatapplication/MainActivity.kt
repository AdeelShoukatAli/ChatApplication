package com.example.adeelahmed.chatapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.example.adeelahmed.chatapplication.Adapters.ViewPagerAdapter
import com.example.adeelahmed.chatapplication.Fragments.SignIn
import com.example.adeelahmed.chatapplication.Fragments.SignUp

class MainActivity : AppCompatActivity() {
companion object {
    var userList:ArrayList<Users> = ArrayList()
    val chatData: ArrayList<Message> = ArrayList()
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var viewPager:ViewPager = findViewById(R.id.view_Pager)
        var tablayout:TabLayout = findViewById(R.id.tabLayout)
        tablayout.setupWithViewPager(viewPager)
        var fragmentsList:ArrayList<Fragment> = ArrayList()
        fragmentsList.add(SignUp())
        fragmentsList.add(SignIn())
        var pagerAdapter:ViewPagerAdapter = ViewPagerAdapter(fragmentsList,this@MainActivity, supportFragmentManager)
        viewPager.adapter = pagerAdapter
        pagerAdapter.notifyDataSetChanged()
    }
}
