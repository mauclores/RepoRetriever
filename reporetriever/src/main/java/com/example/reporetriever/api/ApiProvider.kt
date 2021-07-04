package com.example.reporetriever.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton class to access repository APIs
 */
object ApiProvider {

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(GithubApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient())
            .build()

    /**
     * Customized HTTP Client that can display HTTP logs.
     *
     * @return OKHTTPClient
     */
    private fun getHttpClient(): OkHttpClient {
        val httpLogger = HttpLoggingInterceptor()
        httpLogger.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(httpLogger)
            .build()
    }

    fun <T> createApi(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }
}