package example.dickiezulkarnaen.com.footballappfinal.main.teams.team_detail.team_players.player_detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import example.dickiezulkarnaen.com.footballappfinal.R
import example.dickiezulkarnaen.com.footballappfinal.main.models.Player
import example.dickiezulkarnaen.com.footballappfinal.main.utils.gsonBuilder
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = ""

        setPlayerData()
    }

    private fun setPlayerData() {
        val data = gsonBuilder().fromJson(intent.getStringExtra("player"), Player::class.java)
        val url = data.strThumb
        Picasso.get().load(url).into(imgPlayerFanArt)
        tvPlayerWeight.text = data.strWeight
        tvPlayerHeight.text = data.strHeight
        tvPlayerPosition.text = data.strPosition
        tvPlayerDescription.text = data.strDescriptionEN

        supportActionBar?.title = data.strPlayer
    }
}
