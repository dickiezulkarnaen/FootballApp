package example.dickiezulkarnaen.com.footballappfinal.main.teams.team_detail.team_players

import example.dickiezulkarnaen.com.footballappfinal.main.models.PlayersResponse

interface PlayersView {
    fun onSuccessPlayers(playersResponse: PlayersResponse)
    fun onFailureplayers(message : String)
}