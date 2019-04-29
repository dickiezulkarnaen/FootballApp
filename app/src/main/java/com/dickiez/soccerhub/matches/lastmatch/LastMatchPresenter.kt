package com.dickiez.soccerhub.matches.lastmatch


import com.dickiez.soccerhub.models.MatchResponse
import com.dickiez.soccerhub.service.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LastMatchPresenter(val view: LastMatchView) {

    private val network = NetworkService().getService()
    private val compositeDisposable = CompositeDisposable()

    fun getLastMatch(leagueId: String) {
        val subscribtion = network.getLastMatch(leagueId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onSuccessLastMatch,
                        this::onFailureLastMatch
                )
        compositeDisposable.add(subscribtion)
    }

    private fun onSuccessLastMatch(matchResponse: MatchResponse) {
        view.onSuccessLastMatch(matchResponse)
    }

    private fun onFailureLastMatch(t : Throwable) {
        view.onFailureLastMatch(t.localizedMessage)
    }
}