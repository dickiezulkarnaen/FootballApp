package com.dickiez.soccerhub.matches.nextmatch


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast

import com.dickiez.soccerhub.R
import com.dickiez.soccerhub.base.BaseFragment
import com.dickiez.soccerhub.matches.MatchAdapter
import com.dickiez.soccerhub.models.Event
import com.dickiez.soccerhub.models.MatchResponse
import kotlinx.android.synthetic.main.fragment_next_match.*

class NextMatchFragment : BaseFragment(), NextMatchView, AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener {

    lateinit var presenter: NextMatchPresenter

    private var events : MutableList<Event> = mutableListOf()
    private var adapter : MatchAdapter? = null

    private var leagueNames : ArrayList<String> = arrayListOf()
    private var leagueIds : ArrayList<String> = arrayListOf()
    private var leaguePosition : Int = 0
    private var searchMenu : MenuItem? = null
    private var searchView : SearchView? = null

    private val MATCH_NEXT = "next"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        presenter = NextMatchPresenter(this)
        setLeagues()
    }

    private fun setLeagues() {
        leagueNames.addAll(resources.getStringArray(R.array.league_names))
        leagueIds.addAll(resources.getStringArray(R.array.league_ids))

        val adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, leagueNames)
        spnNextMatchleagues.adapter = adapter
        adapter.notifyDataSetChanged()

        spnNextMatchleagues.onItemSelectedListener = this

        spnNextMatchleagues.setSelection(0)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        getNextMatch(leagueIds[position])
        leaguePosition = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    private fun getNextMatch(leagueId: String) {
        showDialog()
        presenter.getNextMatch(leagueId)
    }

    override fun onSuccessLastMatch(matchResponse: MatchResponse) {
        events.clear()

        if (matchResponse.events.isNullOrEmpty()) {
            rvNextMatch.visibility = View.GONE
            tvNoResultNextMatch.visibility = View.VISIBLE
        } else {
            rvNextMatch.visibility = View.VISIBLE
            tvNoResultNextMatch.visibility = View.GONE
            events.addAll(matchResponse.events)
            adapter = MatchAdapter(context!!, events, MATCH_NEXT)
            adapter?.notifyDataSetChanged()
            rvNextMatch.adapter = adapter
            rvNextMatch.layoutManager = LinearLayoutManager(context)
        }
        hideDialog()
    }

    override fun onFailureLastMatch(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        hideDialog()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search, menu)
        searchMenu = menu?.findItem(R.id.action_search)
        searchView = searchMenu!!.actionView as SearchView
        searchView!!.setOnQueryTextListener(this)
    }

    private fun searchMatch(search : String) {

        var searchEvents : MutableList<Event> = mutableListOf()

        for (event : Event in events) {
            if (event.strHomeTeam.toLowerCase().contains(search.toLowerCase())
                    || event.strAwayTeam.toLowerCase().contains(search.toLowerCase())) {
                searchEvents.add(event)
            }
        }

        adapter?.getSearchMatch(searchEvents)
        adapter?.notifyDataSetChanged()

    }

    override fun onQueryTextSubmit(query: String?): Boolean {return false}

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrBlank()) {
            presenter.getNextMatch(leagueIds[leaguePosition])
        } else {
            searchMatch(newText)
        }
        return true
    }

}
