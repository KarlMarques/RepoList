package com.twisted.minds.repolist.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal fun <S> createRetrofitApi(
    apiClass: Class<S>,
    url: String
): S {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())

    val httpClient = OkHttpClient.Builder()
    httpClient.connectTimeout(50, TimeUnit.SECONDS)
    httpClient.readTimeout(50, TimeUnit.SECONDS)
    httpClient.writeTimeout(50, TimeUnit.SECONDS)

    retrofit.client(httpClient.build())
    return retrofit.build().create(apiClass)
}
