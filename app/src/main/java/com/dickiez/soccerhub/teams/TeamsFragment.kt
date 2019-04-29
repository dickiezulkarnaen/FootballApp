package com.dickiez.soccerhub.teams


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.dickiez.soccerhub.R
import com.dickiez.soccerhub.base.BaseFragment
import com.dickiez.soccerhub.models.Team
import com.dickiez.soccerhub.models.TeamResponse
import kotlinx.android.synthetic.main.fragment_teams.*

class TeamsFragment : BaseFragment(), TeamsView, AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener  {

    private lateinit var presenter: TeamsPresenter

    private var leagueNames: ArrayList<String> = arrayListOf()
    private var leagueIds: ArrayList<String> = arrayListOf()
    private var leaguePosition : Int = 0

    private var teams : MutableList<Team> = mutableListOf()
    private var adapter : TeamsAdapter? = null

    private var searchMenu : MenuItem? = null
    private var searchView : SearchView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = TeamsPresenter(this)
        getLeagues()
    }

    private fun getLeagues() {

        leagueNames.addAll(resources.getStringArray(R.array.league_names))
        leagueIds.addAll(resources.getStringArray(R.array.league_ids))

        val adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, leagueNames)
        spn_leagues.adapter = adapter
        adapter.notifyDataSetChanged()

        spn_leagues.onItemSelectedListener = this

        spn_leagues.setSelection(0)
    }

    private fun getTeams(leagueId: String) {
        showDialog()
        presenter.getTeams(leagueId)
    }

    override fun onSuccessTeams(teamResponse: TeamResponse) {
        teams.clear()

        if (teamResponse.teams.isNullOrEmpty()) {
            tvNoResultTeams.visibility = View.VISIBLE
            rvTeams.visibility = View.GONE
        } else {
            tvNoResultTeams.visibility = View.GONE
            rvTeams.visibility = View.VISIBLE
            teams.addAll(teamResponse.teams)
            adapter = TeamsAdapter(context!!, teams)
            adapter?.notifyDataSetChanged()
            rvTeams.adapter = adapter
            rvTeams.layoutManager = LinearLayoutManager(context)
        }

        hideDialog()
    }

    override fun onFailureTeams(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        hideDialog()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        getTeams(leagueIds[position])
        leaguePosition = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search, menu)
        searchMenu = menu?.findItem(R.id.action_search)
        searchView = searchMenu!!.actionView as SearchView
        searchView!!.setOnQueryTextListener(this)
    }

    private fun searchTeam(search : String) {

        var searchTeams : MutableList<Team> = mutableListOf()

        for (team : Team in teams) {
            if (team.strTeam.toLowerCase().contains(search.toLowerCase())) {
                searchTeams.add(team)
            }
        }

        adapter?.getSearchTeam(searchTeams)
        adapter?.notifyDataSetChanged()

    }

    override fun onQueryTextSubmit(query: String?): Boolean { return false }
    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrBlank()) {
            presenter.getTeams(leagueIds[leaguePosition])
        } else {
            searchTeam(newText)
        }
        return true
    }
}
