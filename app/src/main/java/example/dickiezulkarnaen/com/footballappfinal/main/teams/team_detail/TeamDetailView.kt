package example.dickiezulkarnaen.com.footballappfinal.main.teams.team_detail

import example.dickiezulkarnaen.com.footballappfinal.main.models.TeamResponse

interface TeamDetailView {
    fun onSuccessTeamDetail(teamResponse: TeamResponse)
    fun onFailureTeamDetail(message : String)
}