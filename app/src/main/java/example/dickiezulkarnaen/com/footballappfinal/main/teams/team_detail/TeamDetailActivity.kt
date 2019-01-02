package example.dickiezulkarnaen.com.footballappfinal.main.teams.team_detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.squareup.picasso.Picasso
import example.dickiezulkarnaen.com.footballappfinal.R
import example.dickiezulkarnaen.com.footballappfinal.main.base.BaseActivity
import example.dickiezulkarnaen.com.footballappfinal.main.local_storage.database
import example.dickiezulkarnaen.com.footballappfinal.main.models.FavoriteTeamModel
import example.dickiezulkarnaen.com.footballappfinal.main.models.Team
import example.dickiezulkarnaen.com.footballappfinal.main.models.TeamResponse
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetailActivity : BaseActivity(), TeamDetailView {

    private lateinit var presenter: TeamDetailPresenter
    private lateinit var team : Team

    private var menuItem : Menu? = null
    private var teamId : String = ""
    private var isFavorited : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = ""

        presenter = TeamDetailPresenter(this)

        getTeamDetail()
        checkFavorited()

    }

    private fun getTeamDetail() {
        showDialog()
        teamId = intent.getStringExtra("teamId")
        presenter.getTeamDetail(teamId)
    }

    override fun onSuccessTeamDetail(teamResponse: TeamResponse) {
        team = teamResponse.teams[0]
        Picasso.get().load(team.strTeamBadge).into(imgTeamBadge)
        tvTeamName.text = team.strTeam
        tvTeamBirth.text = team.intFormedYear
        tvTeamPlace.text = team.strStadium
        supportActionBar?.title = team.strTeam

        setViewPager()

        hideDialog()
    }

    override fun onFailureTeamDetail(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        hideDialog()
    }

    private fun setViewPager() {
        vp_team.adapter = TeamPagerAdapter(team, supportFragmentManager)
        tabLayoutTeam.setupWithViewPager(vp_team)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        menuItem = menu!!
        setFavoriteIcon()
        return true
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
        database.use {
            insert(FavoriteTeamModel.TABLE_FAVORITE_TEAM,
                    FavoriteTeamModel.TEAM_ID to team?.idTeam,
                    FavoriteTeamModel.TEAM_NAME to team?.strTeam,
                    FavoriteTeamModel.TEAM_BADGE to team?.strTeamBadge)
        }
        Toast.makeText(this, R.string.added_to_favorite, Toast.LENGTH_SHORT).show()
        isFavorited = true
    }

    private fun deleteFavorite() {
        database.use {
            delete(FavoriteTeamModel.TABLE_FAVORITE_TEAM,
                    FavoriteTeamModel.TEAM_ID+"= {id}",
                    "id" to teamId)
        }
        Toast.makeText(this, R.string.removed_from_favorite, Toast.LENGTH_SHORT).show()
        isFavorited = false
    }

    private fun setFavoriteIcon() {
        if (!isFavorited) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.star_stroke)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.star_solid)
        }
    }

    private fun checkFavorited() {
        var favoritedTeams : ArrayList<String> = arrayListOf()
        database.use {
            val result = select(FavoriteTeamModel.TABLE_FAVORITE_TEAM, FavoriteTeamModel.TEAM_ID)
            favoritedTeams.addAll(result.parseList(classParser()))
        }

        for (i in 0 until favoritedTeams.size) {
            if (teamId.equals(favoritedTeams[i])) {
                isFavorited = true
                break
            }
        }
    }

}
