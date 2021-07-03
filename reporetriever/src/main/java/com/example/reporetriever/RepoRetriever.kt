package com.example.reporetriever

import com.example.reporetriever.api.GithubApi
import com.example.reporetriever.api.SearchRepoResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoRetriever {
    private val api: GithubApi

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
    suspend fun getRepositories(platform: String, org: String): SearchRepoResponse {
        // TODO: Error handling
        val query = "${platform}+org:${org}"
        return api.searchRepos(query)
    }
}