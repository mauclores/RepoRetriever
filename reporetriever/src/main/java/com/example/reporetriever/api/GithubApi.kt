package com.example.reporetriever.api

import com.example.reporetriever.data.SearchRepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("search/repositories")
    suspend fun searchRepos(@Query("q", encoded = true) query: String): Response<SearchRepoResponse?>
}