package com.dickiez.soccerhub.teams.teamdetail.teamplayers

import com.dickiez.soccerhub.models.PlayersResponse
import com.dickiez.soccerhub.service.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PlayersPresenter(val view: PlayersView) {
    private val network = NetworkService().getService()
    private val compositeDisposable = CompositeDisposable()

    fun getTeamDetail(teamId : String) {
        val sub = network.getPlayers(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onSuccessPlayers,
                        this::onFailurePlayers
                )
        compositeDisposable.add(sub)
    }

    private fun onSuccessPlayers(playersResponse: PlayersResponse) {
        view.onSuccessPlayers(playersResponse)
    }

    private fun onFailurePlayers(t : Throwable) {
        view.onFailureplayers(t.localizedMessage)
    }
}