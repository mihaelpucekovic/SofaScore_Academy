package com.sofascoreacademy.sofascoreacademy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sofascoreacademy.model.Team
import com.sofascoreacademy.sofascoreacademy.model.Player
import java.util.*

class MyAdapterRVTeamsPlayers(myDataset: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mDataset: MutableList<Any> = ArrayList()

    class ViewHolder1(v: View) : RecyclerView.ViewHolder(v) {
        var textView: TextView

        init {
            textView = v.findViewById(R.id.textViewTeamName)
        }
    }

    class ViewHolder2(v: View) : RecyclerView.ViewHolder(v) {
        var textView: TextView

        init {
            textView = v.findViewById(R.id.textViewPlayerName)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mDataset[position] is Team) {
            1
        } else {
            2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val viewEvent = LayoutInflater.from(parent.context)
                    .inflate(R.layout.team_view, parent, false)
            ViewHolder1(viewEvent)
        } else {
            val viewTournament = LayoutInflater.from(parent.context)
                    .inflate(R.layout.player_view, parent, false)
            ViewHolder2(viewTournament)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            1 -> {
                val viewHolder0 = holder as ViewHolder1
                val team = mDataset[position] as Team
                viewHolder0.textView.text = team.name
            }
            2 -> {
                val viewHolder2 = holder as ViewHolder2
                val player = mDataset[position] as Player
                viewHolder2.textView.text = player.name
            }
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    fun updateList(newDataset: List<Any>) {
        val diffResult = DiffUtil.calculateDiff(MyDiffUtilCallbackTeamsPlayers(mDataset, newDataset))
        mDataset.clear()
        mDataset.addAll(newDataset)
        diffResult.dispatchUpdatesTo(this)
    }
}