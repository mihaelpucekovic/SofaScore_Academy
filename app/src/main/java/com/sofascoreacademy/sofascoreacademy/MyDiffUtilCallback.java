package com.sofascoreacademy.sofascoreacademy;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.sofascoreacademy.model.Event;
import com.sofascoreacademy.model.Tournament;

import java.util.List;

public class MyDiffUtilCallback extends DiffUtil.Callback {

    List<Object> oldlist;
    List<Object> newlist;

    public MyDiffUtilCallback(List<Object> oldlist, List<Object> newlist) {
        this.oldlist = oldlist;
        this.newlist = newlist;
    }

    @Override
    public int getOldListSize() {
        return oldlist.size();
    }

    @Override
    public int getNewListSize() {
        return newlist.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Object objectOld = oldlist.get(oldItemPosition);
        Object objectNew = oldlist.get(newItemPosition);

        if (objectOld instanceof Event && objectNew instanceof Event) {
            Event eventOld = (Event) objectOld;
            Event eventNew = (Event) objectNew;

            return eventOld.getId() == eventNew.getId();
        } else if (objectOld instanceof Tournament && objectNew instanceof Tournament) {
            Tournament tournamentOld = (Tournament) objectOld;
            Tournament tournamentNew = (Tournament) objectNew;

            return tournamentOld.getId() == tournamentNew.getId();
        } else {
            return false;
        }
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
