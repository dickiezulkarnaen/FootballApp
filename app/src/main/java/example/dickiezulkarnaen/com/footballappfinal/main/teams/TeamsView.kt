package example.dickiezulkarnaen.com.footballappfinal.main.teams

import example.dickiezulkarnaen.com.footballappfinal.main.models.LeagueResponse
import example.dickiezulkarnaen.com.footballappfinal.main.models.TeamResponse

interface TeamsView {
    fun onSuccessTeams(teamResponse: TeamResponse)
    fun onFailureTeams(message : String)
}