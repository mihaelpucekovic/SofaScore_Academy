package com.sofascoreacademy.sofascoreacademy

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sofascoreacademy.model.Event
import com.sofascoreacademy.sofascoreacademy.DetailsFragment.Companion.newInstance

class ViewPagerAdapter(private val mContext: Context, fm: FragmentManager?, events: Array<Event?>) : FragmentPagerAdapter(fm!!) {
    private val tabsCount: Int
    private val events: Array<Event?>
    override fun getItem(position: Int): Fragment {
        return newInstance(position + 1, events)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        return tabsCount
    }

    companion object {
        private lateinit var TAB_TITLES: Array<String?>
    }

    init {
        tabsCount = events.size
        this.events = events
        TAB_TITLES = arrayOfNulls(tabsCount)
        for (i in 0 until tabsCount) {
            TAB_TITLES[i] = "EVENT " + (i + 1)
        }
    }
}