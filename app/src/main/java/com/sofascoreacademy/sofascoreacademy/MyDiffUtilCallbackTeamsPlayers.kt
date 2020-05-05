package com.sofascoreacademy.sofascoreacademy

import androidx.recyclerview.widget.DiffUtil
import com.sofascoreacademy.model.Team
import com.sofascoreacademy.sofascoreacademy.model.Player

class MyDiffUtilCallbackTeamsPlayers(var oldlist: List<Any>, var newlist: List<Any>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldlist.size
    }

    override fun getNewListSize(): Int {
        return newlist.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val objectOld = oldlist[oldItemPosition]
        val objectNew = oldlist[newItemPosition]
        return if (objectOld is Team && objectNew is Team) {
            objectOld.id == objectNew.id
        } else if (objectOld is Player && objectNew is Player) {
            objectOld.id == objectNew.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }

}