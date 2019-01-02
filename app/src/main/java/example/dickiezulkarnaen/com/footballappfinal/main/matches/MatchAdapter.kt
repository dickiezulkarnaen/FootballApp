package example.dickiezulkarnaen.com.footballappfinal.main.matches

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import example.dickiezulkarnaen.com.footballappfinal.R
import example.dickiezulkarnaen.com.footballappfinal.main.matches.match_detail.MatchDetailActivity
import example.dickiezulkarnaen.com.footballappfinal.main.models.Event
import kotlinx.android.synthetic.main.item_match.view.*
import example.dickiezulkarnaen.com.footballappfinal.main.utils.convertStringToLong
import example.dickiezulkarnaen.com.footballappfinal.main.utils.getFormated
import java.util.*
import example.dickiezulkarnaen.com.footballappfinal.main.utils.CalendarPermissionHelper


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

        private val MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 1000

        fun onBindView(event: Event) {

            itemView.tv_date.text = event.dateEvent
            itemView.tv_club1.text = event.strHomeTeam
            itemView.tv_club2.text = event.strAwayTeam
            itemView.tv_score1.text = event.intHomeScore
            itemView.tv_score2.text = event.intAwayScore

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
            val strTime = event.strTime.substring(0, 5)
            val date = convertStringToLong(event.dateEvent, "yyyy-MM-dd")
            val time = convertStringToLong(strTime, "HH:mm")

            val year = getFormated("yyyy", date).toInt()
            val month = getFormated("MM", date).toInt()
            val day = getFormated("dd", date).toInt()
            val hour = getFormated("HH", time).toInt()
            val minute = getFormated("mm", time).toInt()

            cal.set(year, month, day, hour, minute)

            val intent = Intent(Intent.ACTION_INSERT)
            intent.type = "vnd.android.cursor.item/event"
            intent.putExtra("title", event.strHomeTeam+" VS "+event.strAwayTeam)
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.timeInMillis)
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.timeInMillis+3600000)
            itemView.context.startActivity(intent)
        }

        /*fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
            when (requestCode) {
                MY_PERMISSIONS_REQUEST_WRITE_CALENDAR -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    addToCalendar(events!!)
                } else {
                    //code for deny
                }
            }
        }*/

    }
}