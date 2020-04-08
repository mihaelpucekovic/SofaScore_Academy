package com.sofascoreacademy.sofascoreacademy.network;

import com.sofascoreacademy.model.Team;
import com.sofascoreacademy.sofascoreacademy.model.Player;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkAPI {

    @GET("mobile/v4/mcc/{mcc}/teams")
    Single<List<Team>> mccTeams(@Path("mcc") int mcc);

    @GET("mobile/v4/mcc/{mcc}/players")
    Single<List<Player>> mccPlayers(@Path("mcc") int mcc);

    @GET("mobile/v4/team/{id}/details")
    Single<Team> teamDetails(@Path("id") int id);

    @GET("mobile/v4/player/{id}/details")
    Single<Player> playerDetails(@Path("id") int id);
}
