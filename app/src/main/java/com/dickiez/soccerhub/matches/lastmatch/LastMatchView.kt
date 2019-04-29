package com.dickiez.soccerhub.matches.lastmatch

import com.dickiez.soccerhub.models.MatchResponse

interface LastMatchView {
    fun onSuccessLastMatch(matchResponse: MatchResponse)
    fun onFailureLastMatch(message : String)
}