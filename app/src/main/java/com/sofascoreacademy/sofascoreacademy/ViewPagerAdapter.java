package com.sofascoreacademy.sofascoreacademy;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sofascoreacademy.model.Event;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static String[] TAB_TITLES;
    private final Context mContext;
    private final int tabsCount;
    private final Event[] events;

    public ViewPagerAdapter(Context context, FragmentManager fm, Event[] events) {
        super(fm);
        mContext = context;
        this.tabsCount = events.length;
        this.events = events;

        TAB_TITLES = new String[tabsCount];

        for (int i = 0; i < tabsCount; i++) {
            TAB_TITLES[i] = "EVENT " + (i + 1);
        }

    }

    @Override
    public Fragment getItem(int position) {
        return DetailsFragment.newInstance(position + 1, events);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        return tabsCount;
    }
}