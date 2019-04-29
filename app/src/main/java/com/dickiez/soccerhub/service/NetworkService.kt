package com.dickiez.soccerhub.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {

    private val BASE_URL : String = "https://www.thesportsdb.com/api/v1/"

    fun getService() : NetworkApi {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(Logging.LoggingInterceptor.loggingInterceptor())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(NetworkApi::class.java)
        return retrofit
    }
}