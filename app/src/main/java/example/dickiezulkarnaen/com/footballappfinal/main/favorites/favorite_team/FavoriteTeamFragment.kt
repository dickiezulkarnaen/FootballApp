package example.dickiezulkarnaen.com.footballappfinal.main.favorites.favorite_team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*

import example.dickiezulkarnaen.com.footballappfinal.R
import example.dickiezulkarnaen.com.footballappfinal.main.local_storage.database
import example.dickiezulkarnaen.com.footballappfinal.main.models.FavoriteTeamModel
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getFavoriteTeams()
    }

    private fun getFavoriteTeams() {
        var favoriteTeams : List<FavoriteTeamModel>? = arrayListOf()
        context?.database?.use {
            val result = select(FavoriteTeamModel.TABLE_FAVORITE_TEAM)
            val fav = result.parseList(classParser<FavoriteTeamModel>())
            favoriteTeams = fav
        }
        val adapter = FavoriteTeamAdapter(context, favoriteTeams!!)
        rvFavoriteTeam.adapter = adapter
        rvFavoriteTeam.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        getFavoriteTeams()
    }

}
