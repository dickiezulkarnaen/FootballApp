package example.dickiezulkarnaen.com.footballappfinal.main.matches.lastmatch

import example.dickiezulkarnaen.com.footballappfinal.main.models.MatchResponse

interface LastMatchView {
    fun onSuccessLastMatch(matchResponse: MatchResponse)
    fun onFailureLastMatch(message : String)
}