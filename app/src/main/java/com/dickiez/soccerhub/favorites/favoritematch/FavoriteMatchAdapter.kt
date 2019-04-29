package com.dickiez.soccerhub.favorites.favoritematch

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dickiez.soccerhub.R
import com.dickiez.soccerhub.matches.matchdetail.MatchDetailActivity
import com.dickiez.soccerhub.models.FavoriteMatchModel
import kotlinx.android.synthetic.main.item_match.view.*

class FavoriteMatchAdapter(val context: Context?,
                           val schedule : List<FavoriteMatchModel>) : RecyclerView.Adapter<FavoriteMatchAdapter.FavoriteMatchHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return FavoriteMatchHolder(view)
    }

    override fun getItemCount(): Int {
        return schedule.size
    }

    override fun onBindViewHolder(holder: FavoriteMatchHolder, position: Int) {
        holder.bindView(schedule[position])
    }


    inner class FavoriteMatchHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(event: FavoriteMatchModel) {
            itemView.tv_date.text = event.matchDate
            itemView.tv_club1.text = event.homeTeam
            itemView.tv_club2.text = event.awayTeam
            itemView.tv_score1.text = event.homeScore
            itemView.tv_score2.text = event.awayScore

            itemView.setOnClickListener {
                var intent = Intent(itemView.context, MatchDetailActivity::class.java)
                intent.putExtra("matchId", event.matchId)
                itemView.context?.startActivity(intent)
            }
        }
    }
}