package com.example.adeelahmed.chatapplication.Adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.adeelahmed.chatapplication.Fragments.SignIn
import com.example.adeelahmed.chatapplication.Fragments.SignUp

class ViewPagerAdapter(var FragmentList:ArrayList<Fragment>, ctx: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
        when(position) {
            0-> return SignUp()
            1-> return SignIn()
            else -> return SignUp()
        }
    }

    override fun getCount(): Int {
        return FragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position)
        {
            0 -> return "Sign up"
            else -> return "Sign In"
        }
    }
}