package example.dickiezulkarnaen.com.footballappfinal.main.matches


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import example.dickiezulkarnaen.com.footballappfinal.R
import kotlinx.android.synthetic.main.fragment_matches.*

class MatchesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(false)
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setViewPager()
    }

    private fun setViewPager() {
        vp_matches.adapter = MatchesPagerAdapter(childFragmentManager)
        tab_layout.setupWithViewPager(vp_matches)
    }

}
