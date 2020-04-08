package com.sofascoreacademy.sofascoreacademy;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sofascoreacademy.model.Event;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private Event[] events;
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return events[input - 1].getHomeTeam().getName() + " - " + events[input - 1].getAwayTeam().getName();
        }
    });

    public void setIndexAndEvents(int index, Event[] events) {
        mIndex.setValue(index);
        this.events = events;
    }

    public LiveData<String> getText() {
        return mText;
    }
}