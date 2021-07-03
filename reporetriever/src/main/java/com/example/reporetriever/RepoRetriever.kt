package com.example.reporetriever

import com.example.reporetriever.api.SearchRepoApi
import com.example.reporetriever.api.SearchRepoResponse
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoRetriever {
    private val api: SearchRepoApi

    init {
        // It would be better to inject retrofit than instantiating it here
        val retrofit: Retrofit =
            Retrofit.Builder()
                .baseUrl(SearchRepoApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        api = retrofit.create(SearchRepoApi::class.java)
    }

    suspend fun getRepositories(mobilePlatform: String, organization: String): SearchRepoResponse {
        return api.searchRepos()
    }
}