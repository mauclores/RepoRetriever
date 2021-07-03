package com.example.reporetriever.api

import retrofit2.Call
import retrofit2.http.GET

interface SearchRepoApi {

    /* https://api.github.com/search/repositories?q=android+org:rakutentech */

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    // TODO: Pass q and org parameters
    @GET("search/repositories?q=android+org:rakutentech")
    suspend fun searchRepos(): SearchRepoResponse
}