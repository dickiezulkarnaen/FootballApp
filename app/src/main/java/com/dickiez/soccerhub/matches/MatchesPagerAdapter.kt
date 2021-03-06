package com.dickiez.soccerhub.matches

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dickiez.soccerhub.matches.lastmatch.LastMatchFragment
import com.dickiez.soccerhub.matches.nextmatch.NextMatchFragment

class MatchesPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    private val LAST_MATCH = "Last Match"
    private val NEXT_MATCH = "Next Match"

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return LastMatchFragment()
        } else {
            return NextMatchFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return LAST_MATCH
            1 -> return NEXT_MATCH
            else -> return null
        }
    }
}