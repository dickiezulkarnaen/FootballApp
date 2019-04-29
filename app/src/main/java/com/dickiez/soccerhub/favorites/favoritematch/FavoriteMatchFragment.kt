package com.dickiez.soccerhub.favorites.favoritematch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dickiez.soccerhub.R
import com.dickiez.soccerhub.localstorage.database
import com.dickiez.soccerhub.models.FavoriteMatchModel
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getFavoriteMatches()
    }


    private fun getFavoriteMatches() {
        var favoriteMatches : List<FavoriteMatchModel>? = arrayListOf()
        context?.database?.use {
            val result = select(FavoriteMatchModel.TABLE_FAVORITE_MATCH)
            val fav = result.parseList(classParser<FavoriteMatchModel>())
            favoriteMatches = fav
        }
        val adapter = FavoriteMatchAdapter(context, favoriteMatches!!)
        rvFavoriteMatch.adapter = adapter
        rvFavoriteMatch.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        getFavoriteMatches()
    }


}
