package example.dickiezulkarnaen.com.footballappfinal.main.matches.nextmatch

import example.dickiezulkarnaen.com.footballappfinal.main.models.MatchResponse

interface NextMatchView {
    fun onSuccessLastMatch(matchResponse: MatchResponse)
    fun onFailureLastMatch(message : String)
}