package example.dickiezulkarnaen.com.footballappfinal.main.matches.nextmatch

import example.dickiezulkarnaen.com.footballappfinal.main.models.MatchResponse
import example.dickiezulkarnaen.com.footballappfinal.main.service.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NextMatchPresenter(val view: NextMatchView) {

    private val network = NetworkService().getService()
    private val compositeDisposable = CompositeDisposable()

    fun getNextMatch(leagueId: String) {
        val subscribtion = network.getNextMatch(leagueId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onSuccessNextMatch,
                        this::onFailureNextMatch
                )
        compositeDisposable.add(subscribtion)
    }

    private fun onSuccessNextMatch(matchResponse: MatchResponse) {
        view.onSuccessLastMatch(matchResponse)
    }

    private fun onFailureNextMatch(t : Throwable) {
        view.onFailureLastMatch(t.localizedMessage)
    }
}