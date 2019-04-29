package com.dickiez.soccerhub.teams.teamdetail.teamplayers

import com.dickiez.soccerhub.models.PlayersResponse

interface PlayersView {
    fun onSuccessPlayers(playersResponse: PlayersResponse)
    fun onFailureplayers(message : String)
}