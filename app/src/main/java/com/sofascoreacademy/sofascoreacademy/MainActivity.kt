package com.sofascoreacademy.sofascoreacademy

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sofascoreacademy.model.Event
import com.sofascoreacademy.model.Team
import com.sofascoreacademy.model.Tournament
import com.sofascoreacademy.sofascoreacademy.DetailsActivity.Companion.start
import java.util.*

class MainActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private var mAdapter: MyAdapterRV? = null
    private var myAdapterRVTeamsPlayers: MyAdapterRVTeamsPlayers? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDetails = findViewById<Button>(R.id.buttonDetails)
        val buttonZamijeni = findViewById<Button>(R.id.buttonZamijeni)
        val buttonDohvati = findViewById<Button>(R.id.buttonDohvati)

        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(layoutManager)

        mainActivityViewModel.liveDataTeamsAndPlayers.observe(this, androidx.lifecycle.Observer {
            myAdapterRVTeamsPlayers = MyAdapterRVTeamsPlayers(it)
            recyclerView.setAdapter(myAdapterRVTeamsPlayers)
            myAdapterRVTeamsPlayers!!.updateList(it)
        })

        val team1 = Team(1, "Dinamo")
        val team2 = Team(2, "Rijeka")
        val team3 = Team(3, "Zadar")
        val team4 = Team(4, "Osijek")
        val team5 = Team(5, "HNK Gorica")
        val team6 = Team(6, "NK Lokomotiva")
        val team7 = Team(7, "Slaven Belupo")
        val event1 = Event(1, team1, team2)
        val event2 = Event(2, team3, team4)
        val event3 = Event(3, team1, team4)
        val event4 = Event(4, team2, team3)
        val event5 = Event(5, team5, team7)
        val tournament1 = Tournament(1, "Liga prvaka")
        val tournament2 = Tournament(2, "Europska liga")
        val events = arrayOf<Event?>(event1, event2, event3, event4, event5)
        val list: MutableList<Any> = ArrayList()
        list.add(tournament1)
        list.add(event1)
        list.add(event2)
        list.add(event3)
        list.add(event4)
        list.add(tournament2)
        list.add(event5)

        mAdapter = MyAdapterRV(list)
        recyclerView.setAdapter(mAdapter)
        mAdapter!!.updateList(list)

        buttonDetails.setOnClickListener { view: View? -> start(this@MainActivity, events) }

        buttonZamijeni.setOnClickListener { view: View? ->
            Collections.swap(list, 0, 1)
            mAdapter!!.updateList(list)
        }

        buttonDohvati.setOnClickListener { view: View? -> dohvati() }
    }

    fun dohvati() {
        mainActivityViewModel.getTeamsAndPlayers(219, 219)
    }
}