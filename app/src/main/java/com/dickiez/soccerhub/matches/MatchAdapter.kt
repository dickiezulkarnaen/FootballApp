package com.dickiez.soccerhub.matches

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dickiez.soccerhub.R
import com.dickiez.soccerhub.matches.matchdetail.MatchDetailActivity
import com.dickiez.soccerhub.models.Event
import com.dickiez.soccerhub.utils.*
import com.dickiez.soccerhub.utils.CalendarPermissionHelper
import com.dickiez.soccerhub.utils.toGMTFormat
import kotlinx.android.synthetic.main.item_match.view.*
import java.util.*


class MatchAdapter(val context: Context,
                   var matches : MutableList<Event>,
                   val type : String) : RecyclerView.Adapter<MatchAdapter.MatchHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MatchHolder(view)
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: MatchHolder, position: Int) {
        holder.onBindView(matches[position])
    }

    fun getSearchMatch(searchEvents: MutableList<Event>) {
        matches = mutableListOf()
        matches.addAll(searchEvents)
        notifyDataSetChanged()
    }


    inner class MatchHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun onBindView(event: Event) {

            itemView.tv_date.text = event.dateEvent
            itemView.tv_club1.text = event.strHomeTeam
            itemView.tv_club2.text = event.strAwayTeam
            itemView.tv_score1.text = event.intHomeScore
            itemView.tv_score2.text = event.intAwayScore

            Log.d("DATE", "------------>"+event.strDate)
            Log.d("TIME", "------------>"+event.strTime)

           /* val date = toGMTFormat(event.strDate, event.strTime)
            val longDate = dateToLong(date!!)

            itemView.tvMatchTime.text = getFormated("HH:mm", longDate)+" WIB"
            itemView.tv_date.text = getFormated("dd-MM-yyyy", longDate)*/

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MatchDetailActivity::class.java)
                intent.putExtra("matchId", event.idEvent)
                itemView.context.startActivity(intent)
            }

            if (type.equals("next")) {
                itemView.btnAlarmMatch.visibility = View.VISIBLE
            }

            itemView.btnAlarmMatch.setOnClickListener {
                val result = CalendarPermissionHelper(itemView.context).checkPermission()
                if (result) {
                    addToCalendar(event)
                }
            }
        }

        private fun addToCalendar(event: Event) {
            val cal = Calendar.getInstance()
            val date = toGMTFormat(event.strDate, event.strTime)

            cal.time = date

            val intent = Intent(Intent.ACTION_INSERT)
            intent.type = "vnd.android.cursor.item/event"
            intent.putExtra("title", event.strHomeTeam+" VS "+event.strAwayTeam)
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.timeInMillis)
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.timeInMillis+3600000)
            itemView.context.startActivity(intent)
        }
    }
}