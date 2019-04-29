package com.dickiez.soccerhub.favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.dickiez.soccerhub.R
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setViewPager()
    }

    private fun setViewPager() {
        vp_favorites.adapter = FavoritesPagerAdapter(childFragmentManager)
        tab_layout.setupWithViewPager(vp_favorites)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
    }

}
