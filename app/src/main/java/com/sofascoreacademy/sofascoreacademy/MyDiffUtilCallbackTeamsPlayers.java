package com.sofascoreacademy.sofascoreacademy;

import androidx.recyclerview.widget.DiffUtil;

import com.sofascoreacademy.model.Team;
import com.sofascoreacademy.sofascoreacademy.model.Player;

import java.util.List;

public class MyDiffUtilCallbackTeamsPlayers extends DiffUtil.Callback {

    List<Object> oldlist;
    List<Object> newlist;

    public MyDiffUtilCallbackTeamsPlayers(List<Object> oldlist, List<Object> newlist) {
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

        if (objectOld instanceof Team && objectNew instanceof Team) {
            Team teamOld = (Team) objectOld;
            Team teamNew = (Team) objectNew;

            return teamOld.getId() == teamNew.getId();
        } else if (objectOld instanceof Player && objectNew instanceof Player) {
            Player playerOld = (Player) objectOld;
            Player playerNew = (Player) objectNew;

            return playerOld.getId() == playerNew.getId();
        } else {
            return false;
        }
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
