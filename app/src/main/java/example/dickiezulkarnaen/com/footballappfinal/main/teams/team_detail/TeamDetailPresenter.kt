package example.dickiezulkarnaen.com.footballappfinal.main.teams.team_detail

import example.dickiezulkarnaen.com.footballappfinal.main.models.TeamResponse
import example.dickiezulkarnaen.com.footballappfinal.main.service.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TeamDetailPresenter(val view : TeamDetailView) {

    private val network = NetworkService().getService()
    private val compositeDisposable = CompositeDisposable()

    fun getTeamDetail(teamId : String) {
        val sub = network.getTeamDetail(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onSuccessTeamDetail,
                        this::onFailureTeamDetail
                )
        compositeDisposable.add(sub)
    }

    private fun onSuccessTeamDetail(team: TeamResponse) {
        view.onSuccessTeamDetail(team)
    }

    private fun onFailureTeamDetail(t : Throwable) {
        view.onFailureTeamDetail(t.localizedMessage)
    }
}