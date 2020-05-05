package com.sofascoreacademy.sofascoreacademy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofascoreacademy.sofascoreacademy.network.Network
import com.sofascoreacademy.sofascoreacademy.network.NetworkAPI
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _liveDataTeamsAndPlayers = MutableLiveData<List<Any>>()
    val liveDataTeamsAndPlayers = _liveDataTeamsAndPlayers.toImmutableLiveData()

    fun getTeamsAndPlayers(teamsMcc: Int, playersMcc: Int) {
        viewModelScope.launch {
            val network = Network.retrofitInstance!!.create(NetworkAPI::class.java)
            val listTeamsAndPlayers: MutableList<Any> = ArrayList()

            val asyncTeams = async { network.mccTeams(teamsMcc) }
            val asyncPlayers = async { network.mccPlayers(playersMcc) }

            val teams = asyncTeams.await()
            val players = asyncPlayers.await()

            val asyncTeamsDetails = teams.take(10).map {
                async { network.teamDetails(it.id) }
            }

            val asyncPlayersDetails = players.take(10).map {
                async { network.playerDetails(it.id) }
            }

            asyncTeamsDetails.forEach {
                listTeamsAndPlayers.add(it.await())
            }

            asyncPlayersDetails.forEach {
                listTeamsAndPlayers.add(it.await())
            }

            _liveDataTeamsAndPlayers.value = listTeamsAndPlayers
        }
    }
}