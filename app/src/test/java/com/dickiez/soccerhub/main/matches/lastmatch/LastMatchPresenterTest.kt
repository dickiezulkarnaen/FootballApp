package com.dickiez.soccerhub.main.matches.lastmatch

import com.dickiez.soccerhub.matches.lastmatch.LastMatchPresenter
import com.dickiez.soccerhub.matches.lastmatch.LastMatchView
import com.dickiez.soccerhub.models.MatchResponse
import com.dickiez.soccerhub.service.NetworkApi
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class LastMatchPresenterTest {

    @Mock
    private lateinit var network : NetworkApi

    @Mock
    private lateinit var view: LastMatchView

    @Mock
    private lateinit var presenter: LastMatchPresenter

    @Before
    @Throws
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline()}
        MockitoAnnotations.initMocks(this)
        presenter = LastMatchPresenter(view)
    }

    @Test
    fun getLastMatchTest() {
        var matches = mock(MatchResponse::class.java)

        val response : Observable<MatchResponse> = Observable.just(matches)

        `when`(network.getLastMatch("4406")).thenReturn(response)

        presenter.getLastMatch("4406")

        Mockito.verify(view).onSuccessLastMatch(matches)
    }
}

/*
*
* @Mock
    private lateinit var network: Network

    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    @Mock
    private lateinit var presenter: MatchPresenter

    @Before @Throws
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {Schedulers.trampoline()}
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, network, compositeDisposable)
    }

    @Test
    fun getLastMatchTest() {

        var scheduleModel = mock(ScheduleModel::class.java)

        val response : Observable<ScheduleModel> = Observable.just(scheduleModel)

        `when`(network.getLastMatch()).thenReturn(response)

        presenter.getLastMatchData()

        verify(view).onSuccessMatch(scheduleModel)

    }*/