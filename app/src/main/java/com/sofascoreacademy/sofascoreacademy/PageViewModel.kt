package com.sofascoreacademy.sofascoreacademy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sofascoreacademy.model.Event

class PageViewModel : ViewModel() {
    private val mIndex = MutableLiveData<Int>()
    private lateinit var events: Array<Event>
    val text = Transformations.map(mIndex) { input -> events[input - 1].homeTeam.name + " - " + events[input - 1].awayTeam.name }

    fun setIndexAndEvents(index: Int, events: Array<Event>) {
        mIndex.value = index
        this.events = events
    }

}