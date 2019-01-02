package example.dickiezulkarnaen.com.footballappfinal.main.teams

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import example.dickiezulkarnaen.com.footballappfinal.R
import example.dickiezulkarnaen.com.footballappfinal.main.models.Team
import example.dickiezulkarnaen.com.footballappfinal.main.teams.team_detail.TeamDetailActivity
import kotlinx.android.synthetic.main.item_team.view.*

class TeamsAdapter(val context: Context,
                   var teams : MutableList<Team>) : RecyclerView.Adapter<TeamsAdapter.TeamsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return TeamsHolder(view)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: TeamsHolder, position: Int) {
        holder.onBindView(teams[position])
    }

    fun getSearchTeam(searchTeams: MutableList<Team>) {
        teams = mutableListOf()
        teams.addAll(searchTeams)
        notifyDataSetChanged()
    }


    inner class TeamsHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun onBindView(team: Team) {
            Picasso.get().load(team.strTeamBadge).into(itemView.imgItemTeamLogo)
            itemView.tvItemTeamName.text = team.strTeam

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, TeamDetailActivity::class.java)
                intent.putExtra("teamId", team.idTeam)
                itemView.context.startActivity(intent)
            }
        }
    }
}