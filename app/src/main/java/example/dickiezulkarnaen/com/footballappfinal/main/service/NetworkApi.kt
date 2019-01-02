package example.dickiezulkarnaen.com.footballappfinal.main.service

import example.dickiezulkarnaen.com.footballappfinal.main.models.LeagueResponse
import example.dickiezulkarnaen.com.footballappfinal.main.models.MatchResponse
import example.dickiezulkarnaen.com.footballappfinal.main.models.PlayersResponse
import example.dickiezulkarnaen.com.footballappfinal.main.models.TeamResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    @GET("json/1/eventspastleague.php")
    fun getLastMatch(@Query("id") leagueId : String) : Observable<MatchResponse>

    @GET("json/1/eventsnextleague.php")
    fun getNextMatch(@Query("id") leagueId : String) : Observable<MatchResponse>

    @GET("json/1/lookup_all_teams.php")
    fun getTeams(@Query("id") leagueId : String) : Observable<TeamResponse>

    @GET("json/1/lookupevent.php")
    fun getMatchDetail(@Query("id")matchId : String) : Observable<MatchResponse>

    @GET("json/1/lookupteam.php")
    fun getTeamDetail(@Query("id")teamId : String) : Observable<TeamResponse>

    @GET("json/1/lookup_all_players.php")
    fun getPlayers(@Query("id") teamId: String) : Observable<PlayersResponse>

}