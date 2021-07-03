package com.example.reporetriever

import com.example.reporetriever.api.GithubApi
import com.example.reporetriever.api.SearchRepoResponse
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RepoRetriever {
    private var api: GithubApi

    /**
     * Setup dependencies.
     * TODO: Check if manual dependency injection can be used, rather than instantiating here.
     */
    init {
        // Interceptor to log request and response
        val httpLogger = HttpLoggingInterceptor()
        httpLogger.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(httpLogger)
                .build()

        // HTTP Client
        val retrofit: Retrofit =
            Retrofit.Builder()
                .baseUrl(GithubApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        // Search API
        api = retrofit.create(GithubApi::class.java)
    }

    /**
     *
     */
    fun getRepos(platform: String, org: String): SearchRepoResponse? {
        val response = runBlocking {
            api.searchRepos("${platform}+org:${org}")
        }
        if (!response.isSuccessful) {
            throw IOException("Something went wrong: $response")
        }
        return response.body()
    }
}