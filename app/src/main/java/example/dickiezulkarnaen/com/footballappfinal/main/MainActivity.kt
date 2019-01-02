package example.dickiezulkarnaen.com.footballappfinal.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import example.dickiezulkarnaen.com.footballappfinal.R
import example.dickiezulkarnaen.com.footballappfinal.main.favorites.FavoritesFragment
import example.dickiezulkarnaen.com.footballappfinal.main.matches.MatchesFragment
import example.dickiezulkarnaen.com.footballappfinal.main.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener {item ->
            when (item.itemId) {
                R.id.navigation_matches -> {
                    loadMatchesFragment(savedInstanceState)
                }
                R.id.navigation_teams -> {
                    loadTeamsFragment(savedInstanceState)
                }
                R.id.navigation_favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }

        navigation.selectedItemId = R.id.navigation_matches
    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.content_main, MatchesFragment(), MatchesFragment::class.java.simpleName).commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.content_main, TeamsFragment(), TeamsFragment::class.java.simpleName).commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.content_main, FavoritesFragment(), FavoritesFragment::class.java.simpleName).commit()
        }
    }
}
