package com.sofascoreacademy.sofascoreacademy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sofascoreacademy.model.Event;

public class DetailsActivity extends AppCompatActivity {

    public static void start(Context context, Event[] events) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("events", events);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        Event[] events = (Event[]) getIntent().getSerializableExtra("events");

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, getSupportFragmentManager(), events);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}
