package example.dickiezulkarnaen.com.footballappfinal.main.favorites

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import example.dickiezulkarnaen.com.footballappfinal.main.favorites.favorite_match.FavoriteMatchFragment
import example.dickiezulkarnaen.com.footballappfinal.main.favorites.favorite_team.FavoriteTeamFragment

class FavoritesPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    private val MATCHES = "MATCHES"
    private val TEAMS = "TEAMS"

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return FavoriteMatchFragment()
        } else {
            return FavoriteTeamFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return MATCHES
            1 -> return TEAMS
            else -> return null
        }
    }
}