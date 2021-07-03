package com.example.reporetriever.api

import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    /* https://api.github.com/search/repositories?q=android+org:rakutentech */

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("search/repositories")
    suspend fun searchRepos(@Query("q", encoded = true) query: String): SearchRepoResponse
}