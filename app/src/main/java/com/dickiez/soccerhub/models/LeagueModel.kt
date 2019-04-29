package com.dickiez.soccerhub.models
import com.google.gson.annotations.SerializedName



data class LeagueResponse(
    @SerializedName("countrys") val countrys: List<Country>
)

data class Country(
    @SerializedName("idLeague") val idLeague: String,
    @SerializedName("idSoccerXML") val idSoccerXML: String,
    @SerializedName("strSport") val strSport: String,
    @SerializedName("strLeague") val strLeague: String,
    @SerializedName("strLeagueAlternate") val strLeagueAlternate: String,
    @SerializedName("idCup") val idCup: String,
    @SerializedName("intFormedYear") val intFormedYear: String,
    @SerializedName("dateFirstEvent") val dateFirstEvent: String,
    @SerializedName("strGender") val strGender: String,
    @SerializedName("strCountry") val strCountry: String,
    @SerializedName("strWebsite") val strWebsite: String,
    @SerializedName("strFacebook") val strFacebook: String,
    @SerializedName("strTwitter") val strTwitter: String,
    @SerializedName("strYoutube") val strYoutube: String,
    @SerializedName("strRSS") val strRSS: String,
    @SerializedName("strDescriptionEN") val strDescriptionEN: String,
    @SerializedName("strDescriptionDE") val strDescriptionDE: Any,
    @SerializedName("strDescriptionFR") val strDescriptionFR: String,
    @SerializedName("strDescriptionIT") val strDescriptionIT: Any,
    @SerializedName("strDescriptionCN") val strDescriptionCN: Any,
    @SerializedName("strDescriptionJP") val strDescriptionJP: Any,
    @SerializedName("strDescriptionRU") val strDescriptionRU: Any,
    @SerializedName("strDescriptionES") val strDescriptionES: Any,
    @SerializedName("strDescriptionPT") val strDescriptionPT: Any,
    @SerializedName("strDescriptionSE") val strDescriptionSE: Any,
    @SerializedName("strDescriptionNL") val strDescriptionNL: Any,
    @SerializedName("strDescriptionHU") val strDescriptionHU: Any,
    @SerializedName("strDescriptionNO") val strDescriptionNO: Any,
    @SerializedName("strDescriptionPL") val strDescriptionPL: Any,
    @SerializedName("strFanart1") val strFanart1: String,
    @SerializedName("strFanart2") val strFanart2: String,
    @SerializedName("strFanart3") val strFanart3: String,
    @SerializedName("strFanart4") val strFanart4: String,
    @SerializedName("strBanner") val strBanner: String,
    @SerializedName("strBadge") val strBadge: String,
    @SerializedName("strLogo") val strLogo: String,
    @SerializedName("strPoster") val strPoster: String,
    @SerializedName("strTrophy") val strTrophy: String,
    @SerializedName("strNaming") val strNaming: String,
    @SerializedName("strLocked") val strLocked: String
)