package com.dickiez.soccerhub.teams.teamdetail

import com.dickiez.soccerhub.models.TeamResponse

interface TeamDetailView {
    fun onSuccessTeamDetail(teamResponse: TeamResponse)
    fun onFailureTeamDetail(message : String)
}