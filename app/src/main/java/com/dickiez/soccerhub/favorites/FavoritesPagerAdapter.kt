package com.dickiez.soccerhub.favorites

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dickiez.soccerhub.favorites.favoritematch.FavoriteMatchFragment
import com.dickiez.soccerhub.favorites.favoriteteam.FavoriteTeamFragment

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