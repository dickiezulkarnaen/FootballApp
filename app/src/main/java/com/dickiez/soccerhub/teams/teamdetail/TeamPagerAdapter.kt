package com.dickiez.soccerhub.teams.teamdetail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dickiez.soccerhub.models.Team
import com.dickiez.soccerhub.teams.teamdetail.teamoverview.OverviewFragment
import com.dickiez.soccerhub.teams.teamdetail.teamplayers.PlayersFragment

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