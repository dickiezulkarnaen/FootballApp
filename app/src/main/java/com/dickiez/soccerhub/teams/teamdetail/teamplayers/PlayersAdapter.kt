package com.dickiez.soccerhub.teams.teamdetail.teamplayers

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.dickiez.soccerhub.R
import com.dickiez.soccerhub.models.Player
import com.dickiez.soccerhub.teams.teamdetail.teamplayers.playerdetail.PlayerDetailActivity
import com.dickiez.soccerhub.utils.gsonBuilder
import kotlinx.android.synthetic.main.item_player.view.*

class PlayersAdapter(val context: Context, val
                     players : MutableList<Player>) : RecyclerView.Adapter<PlayersAdapter.PlayerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerHolder(view)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bindView(players[position])
    }


    inner class PlayerHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(player: Player) {
            Picasso.get().load(player.strCutout).into(itemView.imgItemPlayerProfile)
            itemView.tvItemPlayerName.text = player.strPlayer
            itemView.tvItemPlayerPosition.text = player.strPosition

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PlayerDetailActivity::class.java)
                val data = gsonBuilder().toJson(player)
                intent.putExtra("player", data)
                itemView.context.startActivity(intent)
            }
        }
    }
}