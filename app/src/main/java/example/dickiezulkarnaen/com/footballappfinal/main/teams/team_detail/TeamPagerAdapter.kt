package example.dickiezulkarnaen.com.footballappfinal.main.teams.team_detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import example.dickiezulkarnaen.com.footballappfinal.main.models.Team
import example.dickiezulkarnaen.com.footballappfinal.main.teams.team_detail.team_overview.OverviewFragment
import example.dickiezulkarnaen.com.footballappfinal.main.teams.team_detail.team_players.PlayersFragment

class TeamPagerAdapter(val team : Team, fm : FragmentManager) : FragmentPagerAdapter(fm) {

    private val OVERVIEW = "OVERVIEW"
    private val PLAYERS = "PLAYERS"

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return OverviewFragment(team)
        } else {
            return PlayersFragment(team)
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return OVERVIEW
            1 -> return PLAYERS
            else -> return null
        }
    }
}