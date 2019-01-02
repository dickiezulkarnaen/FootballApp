package example.dickiezulkarnaen.com.footballappfinal.main.matches.lastmatch

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast

import example.dickiezulkarnaen.com.footballappfinal.R
import example.dickiezulkarnaen.com.footballappfinal.main.base.BaseFragment
import example.dickiezulkarnaen.com.footballappfinal.main.matches.MatchAdapter
import example.dickiezulkarnaen.com.footballappfinal.main.models.Event
import example.dickiezulkarnaen.com.footballappfinal.main.models.MatchResponse
import kotlinx.android.synthetic.main.fragment_last_match.*

class LastMatchFragment : BaseFragment(), LastMatchView, AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener {

    private lateinit var presenter: LastMatchPresenter
    private var events : MutableList<Event> = mutableListOf()
    private var adapter : MatchAdapter? = null

    private var leagueNames : ArrayList<String> = arrayListOf()
    private var leagueIds : ArrayList<String> = arrayListOf()
    private var leaguePosition : Int = 0
    private var searchMenu : MenuItem? = null
    private var searchView : SearchView? = null

    private val MATCH_LAST = "last"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        presenter = LastMatchPresenter(this)
        setLeagues()
    }

    private fun setLeagues() {
        leagueNames.addAll(resources.getStringArray(R.array.league_names))
        leagueIds.addAll(resources.getStringArray(R.array.league_ids))

        val adapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, leagueNames)
        spnLastMatchleagues.adapter = adapter
        adapter.notifyDataSetChanged()

        spnLastMatchleagues.onItemSelectedListener = this

        spnLastMatchleagues.setSelection(0)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        getLastMatch(leagueIds[position])
        leaguePosition = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    private fun getLastMatch(leagueId: String) {
        showDialog()
        presenter.getLastMatch(leagueId)
    }

    override fun onSuccessLastMatch(matchResponse: MatchResponse) {
        events.clear()

        if (matchResponse.events.isNullOrEmpty()) {
            rvLastMatch.visibility = View.GONE
            tvNoResultLastMatch.visibility = View.VISIBLE
        } else {
            rvLastMatch.visibility = View.VISIBLE
            tvNoResultLastMatch.visibility = View.GONE
            events.addAll(matchResponse.events)
            adapter = MatchAdapter(context!!, events, MATCH_LAST)
            adapter?.notifyDataSetChanged()
            rvLastMatch.adapter = adapter
            rvLastMatch.layoutManager = LinearLayoutManager(context)
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
            if (search.toLowerCase().contains(event.strHomeTeam.toLowerCase())
                    || search.toLowerCase().contains(event.strAwayTeam.toLowerCase())) {
                searchEvents.add(event)
            }
        }

        adapter?.getSearchMatch(searchEvents)
        adapter?.notifyDataSetChanged()

    }

    override fun onQueryTextSubmit(query: String?): Boolean { return false }
    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrBlank()) {
            presenter.getLastMatch(leagueIds[leaguePosition])
        } else {
            searchMatch(newText!!)
        }
        return true
    }
}