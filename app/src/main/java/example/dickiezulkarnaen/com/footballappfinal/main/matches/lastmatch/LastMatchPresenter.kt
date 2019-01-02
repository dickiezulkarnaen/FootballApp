package example.dickiezulkarnaen.com.footballappfinal.main.matches.lastmatch


import example.dickiezulkarnaen.com.footballappfinal.main.models.MatchResponse
import example.dickiezulkarnaen.com.footballappfinal.main.service.NetworkService
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