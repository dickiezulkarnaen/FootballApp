package example.dickiezulkarnaen.com.footballappfinal.main.teams

import example.dickiezulkarnaen.com.footballappfinal.main.models.LeagueResponse
import example.dickiezulkarnaen.com.footballappfinal.main.models.TeamResponse
import example.dickiezulkarnaen.com.footballappfinal.main.service.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TeamsPresenter(val view: TeamsView) {

    private val network = NetworkService().getService()
    private val compositeDisposable = CompositeDisposable()

    fun getTeams(leagueId : String) {
        val subscribtion = network.getTeams(leagueId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onSuccessTeams,
                        this::onFailureTeams
                )
        compositeDisposable.add(subscribtion)
    }

    private fun onSuccessTeams(teamResponse: TeamResponse) {
        view.onSuccessTeams(teamResponse)
    }

    private fun onFailureTeams(t : Throwable) {
        view.onFailureTeams(t.localizedMessage)
    }
}