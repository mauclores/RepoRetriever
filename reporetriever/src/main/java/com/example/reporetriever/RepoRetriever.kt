package com.example.reporetriever

import com.example.reporetriever.api.ApiProvider
import com.example.reporetriever.api.GithubApi
import com.example.reporetriever.data.SearchRepoResponse
import kotlinx.coroutines.*
import java.io.IOException

class RepoRetriever {
    private var api: GithubApi = ApiProvider.createApi(GithubApi::class.java)

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