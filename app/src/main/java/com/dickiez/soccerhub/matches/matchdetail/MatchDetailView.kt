package com.dickiez.soccerhub.matches.matchdetail

import com.dickiez.soccerhub.models.MatchResponse
import com.dickiez.soccerhub.models.TeamResponse

interface MatchDetailView {
    fun onSuccessMatchDetail(matchResponse: MatchResponse)
    fun onFailureMatchDetail(message : String)
    fun onSuccessHomeTeamDetail(team : TeamResponse)
    fun onFailureHomeTeamDetail(message : String)
    fun onSuccessAwayTeamDetail(team : TeamResponse)
    fun onFailureAwayTeamDetail(message : String)
}