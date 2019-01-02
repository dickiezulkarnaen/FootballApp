package example.dickiezulkarnaen.com.footballappfinal.main.favorites.favorite_team

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import example.dickiezulkarnaen.com.footballappfinal.R
import example.dickiezulkarnaen.com.footballappfinal.main.models.FavoriteTeamModel
import example.dickiezulkarnaen.com.footballappfinal.main.teams.team_detail.TeamDetailActivity
import kotlinx.android.synthetic.main.item_team.view.*

class FavoriteTeamAdapter(val context: Context?,
                            val teams : List<FavoriteTeamModel>) : RecyclerView.Adapter<FavoriteTeamAdapter.FavoriteTeamHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return FavoriteTeamHolder(view)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: FavoriteTeamHolder, position: Int) {
        holder.onBindView(teams[position])
    }

    inner class FavoriteTeamHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun onBindView(team: FavoriteTeamModel) {
            Picasso.get().load(team.teamBadge).into(itemView.imgItemTeamLogo)
            itemView.tvItemTeamName.text = team.teamName

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, TeamDetailActivity::class.java)
                intent.putExtra("teamId", team.teamId)
                itemView.context.startActivity(intent)
            }
        }
    }
}

