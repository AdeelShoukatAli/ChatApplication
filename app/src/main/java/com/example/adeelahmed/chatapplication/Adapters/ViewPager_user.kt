package com.example.adeelahmed.chatapplication.Adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.adeelahmed.chatapplication.Fragments.chatsFragment
import com.example.adeelahmed.chatapplication.Fragments.users_Fragment

class ViewPager_user (ctx:Context, fm:FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
       when(position) {
           0 -> return chatsFragment()
           else -> return users_Fragment()
       }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> return "Chats"
            else -> return "Active Users"
        }
    }
}