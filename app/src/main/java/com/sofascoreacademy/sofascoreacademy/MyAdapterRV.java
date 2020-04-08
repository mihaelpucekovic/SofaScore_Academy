package com.sofascoreacademy.sofascoreacademy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sofascoreacademy.model.Event;
import com.sofascoreacademy.model.Tournament;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterRV extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> mDataset = new ArrayList<>();

    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder1(View v) {
            super(v);
            textView = v.findViewById(R.id.textViewEventName);
        }
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder2(View v) {
            super(v);
            textView = v.findViewById(R.id.textViewTournamentName);
        }
    }

    public MyAdapterRV(List<Object> myDataset) {
        //mDataset = myDataset;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataset.get(position) instanceof Event) {
            return 1;
        }
        else {
            return 2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

        if (viewType == 1) {
            View viewEvent = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_view, parent, false);

            return new ViewHolder1(viewEvent);
        }
        else {
            View viewTournament = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.tournament_view, parent, false);

            return new ViewHolder2(viewTournament);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 1:
                ViewHolder1 viewHolder0 = (ViewHolder1) holder;

                Event event = (Event) mDataset.get(position);

                viewHolder0.textView.setText(event.getHomeTeam().getName() + " - " + event.getAwayTeam().getName());

                break;

            case 2:
                ViewHolder2 viewHolder2 = (ViewHolder2) holder;

                Tournament tournament = (Tournament) mDataset.get(position);

                viewHolder2.textView.setText(tournament.getName());

                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void updateList(List<Object> newDataset) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtilCallback(mDataset, newDataset));
        mDataset.clear();
        mDataset.addAll(newDataset);
        diffResult.dispatchUpdatesTo(this);
    }
}
