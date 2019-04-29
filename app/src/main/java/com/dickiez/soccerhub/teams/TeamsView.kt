package com.dickiez.soccerhub.teams

import com.dickiez.soccerhub.models.TeamResponse

interface TeamsView {
    fun onSuccessTeams(teamResponse: TeamResponse)
    fun onFailureTeams(message : String)
}