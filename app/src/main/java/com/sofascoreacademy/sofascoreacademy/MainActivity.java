package com.sofascoreacademy.sofascoreacademy;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sofascoreacademy.model.Event;
import com.sofascoreacademy.model.Team;
import com.sofascoreacademy.model.Tournament;
import com.sofascoreacademy.sofascoreacademy.model.Player;
import com.sofascoreacademy.sofascoreacademy.network.Network;
import com.sofascoreacademy.sofascoreacademy.network.NetworkAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private MyAdapterRV mAdapter;
    private MyAdapterRVTeamsPlayers myAdapterRVTeamsPlayers;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonDetails = findViewById(R.id.buttonDetails);
        Button buttonZamijeni = findViewById(R.id.buttonZamijeni);
        Button buttonDohvati = findViewById(R.id.buttonDohvati);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Team team1 = new Team(1, "Dinamo");
        Team team2 = new Team(2, "Rijeka");
        Team team3 = new Team(3, "Zadar");
        Team team4 = new Team(4, "Osijek");
        Team team5 = new Team(5, "HNK Gorica");
        Team team6 = new Team(6, "NK Lokomotiva");
        Team team7 = new Team(7, "Slaven Belupo");
        Event event1 = new Event(1, team1, team2);
        Event event2 = new Event(2, team3, team4);
        Event event3 = new Event(3, team1, team4);
        Event event4 = new Event(4, team2, team3);
        Event event5 = new Event(5, team5, team7);
        Tournament tournament1 = new Tournament(1, "Liga prvaka");
        Tournament tournament2 = new Tournament(2, "Europska liga");

        final Event[] events = {event1, event2, event3, event4, event5};

        final List<Object> list = new ArrayList<>();
        list.add(tournament1);
        list.add(event1);
        list.add(event2);
        list.add(event3);
        list.add(event4);
        list.add(tournament2);
        list.add(event5);

        mAdapter = new MyAdapterRV(list);
        recyclerView.setAdapter(mAdapter);
        mAdapter.updateList(list);

        buttonDetails.setOnClickListener((View view) -> {
                DetailsActivity.start(MainActivity.this, events);
        });

        buttonZamijeni.setOnClickListener((View view) -> {
                Collections.swap(list, 0, 1);

                mAdapter.updateList(list);
        });

        buttonDohvati.setOnClickListener((View view) -> {
                getTeamsAndPlayersRx();
                //getTeamsData();
        });
    }

    private void getTeamsAndPlayersRx() {
        Single<List<Team>> mccTeams = Network.getRetrofitInstance().create(NetworkAPI.class).mccTeams(219);
        Single<List<Player>> mccPlayers = Network.getRetrofitInstance().create(NetworkAPI.class).mccPlayers(219);

        Single.zip(mccTeams, mccPlayers, (teams, players) -> {
            List<Object> list = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                list.add(teams.get(i));
                list.add(players.get(i));
            }

            return list;
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(objects -> {
                    List<Object> newObjectsWithDetails = new ArrayList<>();

                    for (Object object : objects) {
                        if (object instanceof Team) {
                            Team team = (Team) object;

                            Network.getRetrofitInstance().create(NetworkAPI.class).teamDetails(team.getId())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(teamMoreDetails -> {
                                        newObjectsWithDetails.add(teamMoreDetails);

                                        myAdapterRVTeamsPlayers = new MyAdapterRVTeamsPlayers(newObjectsWithDetails);
                                        recyclerView.setAdapter(myAdapterRVTeamsPlayers);
                                        myAdapterRVTeamsPlayers.updateList(newObjectsWithDetails);

                                    }, throwable -> {
                                        Log.d("testTag", "error" + throwable.getMessage());
                                    });
                        }
                        else  if (object instanceof Player) {
                            Player player = (Player) object;

                            Network.getRetrofitInstance().create(NetworkAPI.class).playerDetails(player.getId())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(playerMoreDetails -> {
                                        newObjectsWithDetails.add(playerMoreDetails);

                                        myAdapterRVTeamsPlayers = new MyAdapterRVTeamsPlayers(newObjectsWithDetails);
                                        recyclerView.setAdapter(myAdapterRVTeamsPlayers);
                                        myAdapterRVTeamsPlayers.updateList(newObjectsWithDetails);

                                    }, throwable -> {
                                        Log.d("testTag", "error" + throwable.getMessage());
                                    });
                        }
                    }
        }, throwable -> {
            Log.d("testTag", "error: " + throwable.getMessage());
        });
    }

    /*private void getTeamsData() {
        execute(Network.getRetrofitInstance().create(NetworkAPI.class).mccTeams(219), new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                getPlayersData(response.body());
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable throwable) {
                Log.d("Error", "Error: " + throwable.getMessage());
            }
        });
    }

    private void getPlayersData(final List<Team> teams) {
        execute(Network.getRetrofitInstance().create(NetworkAPI.class).mccPlayers(219), new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                showTeamsAndPlayers(teams, response.body());
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable throwable) {
                Log.d("Error", "Error: " + throwable.getMessage());
            }
        });
    }

    private void showTeamsAndPlayers(List<Team> teams, List<Player> players) {
        final List<Object> list = new ArrayList<>();

        for (Team team : teams) {
            list.add(team);

            for (Player player : players) {
                if (player.getTeam().getId() == team.getId()) {
                    list.add(player);
                }
            }
        }

        myAdapterRVTeamsPlayers = new MyAdapterRVTeamsPlayers(list);
        recyclerView.setAdapter(myAdapterRVTeamsPlayers);
        myAdapterRVTeamsPlayers.updateList(list);
    }*/
}