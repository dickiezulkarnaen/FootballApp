package example.dickiezulkarnaen.com.footballappfinal.main.matches.match_detail

import example.dickiezulkarnaen.com.footballappfinal.main.models.MatchResponse
import example.dickiezulkarnaen.com.footballappfinal.main.models.TeamResponse

interface MatchDetailView {
    fun onSuccessMatchDetail(matchResponse: MatchResponse)
    fun onFailureMatchDetail(message : String)
    fun onSuccessHomeTeamDetail(team : TeamResponse)
    fun onFailureHomeTeamDetail(message : String)
    fun onSuccessAwayTeamDetail(team : TeamResponse)
    fun onFailureAwayTeamDetail(message : String)
}