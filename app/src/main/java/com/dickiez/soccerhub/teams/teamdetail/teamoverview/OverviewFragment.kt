package com.dickiez.soccerhub.teams.teamdetail.teamoverview


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dickiez.soccerhub.R
import com.dickiez.soccerhub.models.Team
import kotlinx.android.synthetic.main.fragment_overview.*

@SuppressLint("ValidFragment")
class OverviewFragment(val team : Team) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvTeamOverview.text = team.strDescriptionEN
    }

}
