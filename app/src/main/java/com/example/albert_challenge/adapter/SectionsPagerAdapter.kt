package com.example.albert_challenge.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.albert_challenge.R
import com.example.albert_challenge.ui.main.SearchFragment
import com.example.albert_challenge.ui.main.WishListFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a SearchFragment (defined as a static inner class below).
        return when (position) {
            0 -> SearchFragment.newInstance(position)
            else -> {
                return WishListFragment.newInstance(position)
            }
        }
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}