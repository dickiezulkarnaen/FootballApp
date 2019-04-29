package com.dickiez.soccerhub.matches.matchdetail

import com.dickiez.soccerhub.models.MatchResponse
import com.dickiez.soccerhub.models.TeamResponse
import com.dickiez.soccerhub.service.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MatchDetailPresenter(val view: MatchDetailView) {

    private val network = NetworkService().getService()
    private val compositeDisposable = CompositeDisposable()

    fun getMatchDetail(matchId : String) {
        val subscribtion = network.getMatchDetail(matchId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onSuccessMatchDetail,
                        this::onFailureMatchDetail
                )
        compositeDisposable.add(subscribtion)
    }

    private fun onSuccessMatchDetail(matchResponse: MatchResponse) {
        view.onSuccessMatchDetail(matchResponse)
    }

    private fun onFailureMatchDetail(t : Throwable) {
        view.onFailureMatchDetail(t.localizedMessage)
    }

    //========HOME DETAIL==============================
    fun getHomeTeamDetail(homeTeamId : String) {
        val sub = network.getTeamDetail(homeTeamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onSuccessHomeTeamDetail,
                        this::onFailureHomeTeamDetail
                )
        compositeDisposable.add(sub)
    }

    private fun onSuccessHomeTeamDetail(team: TeamResponse) {
        view.onSuccessHomeTeamDetail(team)
    }

    fun onFailureHomeTeamDetail(t : Throwable) {
        view.onFailureHomeTeamDetail(t.localizedMessage)
    }



    //=========AWAY DETAIL=============================
    fun getAwayTeamDetail(awayTeamId : String) {
        val sub = network.getTeamDetail(awayTeamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onSuccessAwayTeamDetail,
                        this::onFailureAwayTeamDetail
                )
        compositeDisposable.add(sub)
    }

    private fun onSuccessAwayTeamDetail(team: TeamResponse) {
        view.onSuccessAwayTeamDetail(team)
    }

    private fun onFailureAwayTeamDetail(t : Throwable) {
        view.onFailureAwayTeamDetail(t.localizedMessage)
    }
}