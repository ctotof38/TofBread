package com.totof.bread

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val lists = ArrayList<Fragment>()

    override fun getItemCount(): Int = lists.size

    override fun createFragment(position: Int): Fragment {
        return lists[position]
    }

    fun addFragment(fragment: Fragment?) {
        fragment?.let { lists.add(it) }
    }

    fun getFragment(position: Int): Fragment? {
        return if (position in 0 until lists.size) {
            lists[position]
        } else null
    }
}
