package com.dickiez.soccerhub.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class Logging {
    object LoggingInterceptor {
        fun loggingInterceptor() : OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient().newBuilder()
                    .addInterceptor(getInterceptor())
                    .addInterceptor(logging)
                    .build()

            return client
        }

        private fun getInterceptor(): Interceptor {
            return Interceptor { chain ->
                val request = chain.request()
                val builder = request?.newBuilder()?.addHeader("Content-Type","application/json")
                chain.proceed(builder!!.build())
            }
        }
    }
}