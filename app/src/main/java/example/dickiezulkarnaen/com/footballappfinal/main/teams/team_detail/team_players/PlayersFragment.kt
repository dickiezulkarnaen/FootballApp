package example.dickiezulkarnaen.com.footballappfinal.main.teams.team_detail.team_players


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import example.dickiezulkarnaen.com.footballappfinal.R
import example.dickiezulkarnaen.com.footballappfinal.main.base.BaseFragment
import example.dickiezulkarnaen.com.footballappfinal.main.models.Player
import example.dickiezulkarnaen.com.footballappfinal.main.models.PlayersResponse
import example.dickiezulkarnaen.com.footballappfinal.main.models.Team
import kotlinx.android.synthetic.main.fragment_players.*


@SuppressLint("ValidFragment")
class PlayersFragment(val team: Team) : BaseFragment(), PlayersView {

    private lateinit var presenter : PlayersPresenter
    private var players : MutableList<Player> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = PlayersPresenter(this)
        getPlayers()
    }

    private fun getPlayers() {
        showDialog()
        presenter.getTeamDetail(team.idTeam)
    }

    override fun onSuccessPlayers(playersResponse: PlayersResponse) {
        if (playersResponse.player.isNullOrEmpty()) {
            rvPlayers.visibility = View.GONE
            tvNoResultPlayer.visibility = View.VISIBLE
        } else {
            rvPlayers.visibility = View.VISIBLE
            tvNoResultPlayer.visibility = View.GONE
            players.addAll(playersResponse.player)
            val adapter = PlayersAdapter(context!!, players)
            adapter.notifyDataSetChanged()
            rvPlayers.adapter = adapter
            rvPlayers.layoutManager = LinearLayoutManager(context)
        }

        hideDialog()
    }

    override fun onFailureplayers(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        hideDialog()
    }

}
