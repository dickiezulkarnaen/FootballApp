package example.dickiezulkarnaen.com.footballappfinal.main.models

data class FavoriteMatchModel(val id : Int,
                              val matchId : String,
                              val matchDate : String,
                              val homeTeam : String,
                              val awayTeam : String,
                              val homeScore : String,
                              val awayScore : String) {
    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }
}