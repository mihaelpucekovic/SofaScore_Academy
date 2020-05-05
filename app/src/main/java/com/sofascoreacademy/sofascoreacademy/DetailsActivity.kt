package com.sofascoreacademy.sofascoreacademy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sofascoreacademy.model.Event

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)
        val events = intent.getSerializableExtra("events") as Array<Event?>
        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager, events)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = viewPagerAdapter
        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    companion object {
        @JvmStatic
        fun start(context: Context, events: Array<Event?>?) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("events", events)
            context.startActivity(intent)
        }
    }
}