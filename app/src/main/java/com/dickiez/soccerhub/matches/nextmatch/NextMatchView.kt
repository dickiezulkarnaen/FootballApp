package com.dickiez.soccerhub.matches.nextmatch

import com.dickiez.soccerhub.models.MatchResponse

interface NextMatchView {
    fun onSuccessLastMatch(matchResponse: MatchResponse)
    fun onFailureLastMatch(message : String)
}