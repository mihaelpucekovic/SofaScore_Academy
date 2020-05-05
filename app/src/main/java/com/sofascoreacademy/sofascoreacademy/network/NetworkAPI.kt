package com.sofascoreacademy.sofascoreacademy.network

import com.sofascoreacademy.model.Team
import com.sofascoreacademy.sofascoreacademy.model.Player
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkAPI {
    @GET("mobile/v4/mcc/{mcc}/teams")
    suspend fun mccTeams(@Path("mcc") mcc: Int): List<Team>

    @GET("mobile/v4/mcc/{mcc}/players")
    suspend fun mccPlayers(@Path("mcc") mcc: Int): List<Player>

    @GET("mobile/v4/team/{id}/details")
    suspend fun teamDetails(@Path("id") id: Int): Team

    @GET("mobile/v4/player/{id}/details")
    suspend fun playerDetails(@Path("id") id: Int): Player
}