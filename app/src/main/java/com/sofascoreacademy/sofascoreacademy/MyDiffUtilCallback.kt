package com.sofascoreacademy.sofascoreacademy

import androidx.recyclerview.widget.DiffUtil
import com.sofascoreacademy.model.Event
import com.sofascoreacademy.model.Tournament

class MyDiffUtilCallback(var oldlist: List<Any>, var newlist: List<Any>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldlist.size
    }

    override fun getNewListSize(): Int {
        return newlist.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val objectOld = oldlist[oldItemPosition]
        val objectNew = oldlist[newItemPosition]
        return if (objectOld is Event && objectNew is Event) {
            objectOld.id == objectNew.id
        } else if (objectOld is Tournament && objectNew is Tournament) {
            objectOld.id == objectNew.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }

}