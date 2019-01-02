package example.dickiezulkarnaen.com.footballappfinal.main.matches.match_detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import example.dickiezulkarnaen.com.footballappfinal.R
import example.dickiezulkarnaen.com.footballappfinal.main.base.BaseActivity
import example.dickiezulkarnaen.com.footballappfinal.main.local_storage.database
import example.dickiezulkarnaen.com.footballappfinal.main.models.Event
import example.dickiezulkarnaen.com.footballappfinal.main.models.FavoriteMatchModel
import example.dickiezulkarnaen.com.footballappfinal.main.models.MatchResponse
import example.dickiezulkarnaen.com.footballappfinal.main.models.TeamResponse
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MatchDetailActivity : BaseActivity(), MatchDetailView {

    private lateinit var presenter: MatchDetailPresenter
    private lateinit var match : Event
    private var menuItem : Menu? = null
    private var matchId : String = ""
    private var isFavorited : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        presenter = MatchDetailPresenter(this)

        getMatchDetail()
        checkFavorited()

    }

    private fun getMatchDetail() {
        showDialog()
        matchId = intent.getStringExtra("matchId")
        presenter.getMatchDetail(matchId)
    }

    override fun onSuccessMatchDetail(matchResponse: MatchResponse) {
        layout_detail.visibility = View.VISIBLE
        match = matchResponse.events[0]

        presenter.getHomeTeamDetail(match.idHomeTeam)
        presenter.getAwayTeamDetail(match.idAwayTeam)

        tv_date_detail.text = match.dateEvent
        tv_club1_detail.text = match.strHomeTeam
        tv_club2_detail.text = match.strAwayTeam
        tv_score1_detail.text = match.intHomeScore
        tv_score2_detail.text = match.intAwayScore
        tv_goal1_detail.text = match.strHomeGoalDetails
        tv_goal2_detail.text = match.strAwayGoalDetails
        tv_shot1_detail.text = match.intHomeShots
        tv_shot2_detail.text = match.intAwayShots
        tv_keeper1_detail.text = match.strHomeLineupGoalkeeper
        tv_keeper2_detail.text = match.strAwayLineupGoalkeeper
        tv_defence1_detail.text = match.strHomeLineupDefense
        tv_defence2_detail.text = match.strAwayLineupDefense
        tv_midfield1_detail.text = match.strHomeLineupMidfield
        tv_midfield2_detail.text = match.strAwayLineupMidfield
        tv_forward1_detail.text = match.strHomeLineupForward
        tv_forward2_detail.text = match.strAwayLineupForward
        tv_subtitutes1_detail.text = match.strHomeLineupSubstitutes
        tv_subtitutes2_detail.text = match.strAwayLineupSubstitutes
        hideDialog()
    }

    override fun onFailureMatchDetail(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        hideDialog()
    }

    override fun onSuccessHomeTeamDetail(team: TeamResponse) {
        Picasso.get().load(team.teams[0].strTeamBadge).into(img_club1_detail)
    }

    override fun onFailureHomeTeamDetail(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessAwayTeamDetail(team: TeamResponse) {
        Picasso.get().load(team.teams[0].strTeamBadge).into(img_club2_detail)
    }

    override fun onFailureAwayTeamDetail(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        menuItem = menu
        setFavoriteIcon()
        return true
    }

    private fun setFavoriteIcon() {
        if (!isFavorited) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.star_stroke)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.star_solid)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.favorite -> {
                if (!isFavorited) {
                    addToFavorite()
                    setFavoriteIcon()
                } else {
                    deleteFavorite()
                    setFavoriteIcon()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addToFavorite() {
        var homeScore = ""
        if (match?.intHomeScore != null) {
            homeScore = match!!.intHomeScore
        }

        var awayScore = ""
        if (match?.intAwayScore != null) {
            awayScore = match!!.intAwayScore
        }

        database.use {
            insert(FavoriteMatchModel.TABLE_FAVORITE_MATCH,
                    FavoriteMatchModel.MATCH_ID to match?.idEvent,
                    FavoriteMatchModel.MATCH_DATE to match?.dateEvent,
                    FavoriteMatchModel.HOME_TEAM to match?.strHomeTeam,
                    FavoriteMatchModel.AWAY_TEAM to match?.strAwayTeam,
                    FavoriteMatchModel.HOME_SCORE to homeScore,
                    FavoriteMatchModel.AWAY_SCORE to awayScore)
        }
        Toast.makeText(this, R.string.added_to_favorite, Toast.LENGTH_SHORT).show()
        isFavorited = true
    }

    private fun deleteFavorite() {
        database.use {
            delete(FavoriteMatchModel.TABLE_FAVORITE_MATCH,
                    FavoriteMatchModel.MATCH_ID+"= {id}",
                    "id" to matchId)
        }
        Toast.makeText(this, R.string.removed_from_favorite, Toast.LENGTH_SHORT).show()
        isFavorited = false
    }

    private fun checkFavorited() {
        var favoritedMatches : ArrayList<String> = arrayListOf()
        database.use {
            val result = select(FavoriteMatchModel.TABLE_FAVORITE_MATCH, FavoriteMatchModel.MATCH_ID)
            favoritedMatches.addAll(result.parseList(classParser()))
        }

        for (i in 0 until favoritedMatches.size) {
            if (matchId.equals(favoritedMatches[i])) {
                isFavorited = true
                break
            }
        }
    }
}
