package com.example.reporetriever

import android.util.Log
import com.example.reporetriever.api.ApiProvider
import com.example.reporetriever.api.GithubApi
import com.example.reporetriever.data.Item
import java.io.IOException

/**
 * Class exposing methods to access repository information search
 */
class RepoRetriever(repoApi: GithubApi) {
    private var api: GithubApi = repoApi

    constructor(): this(ApiProvider.createApi(GithubApi::class.java))

    /**
     * Gets a list of repository information for an organization for a given platform.
     *
     * @param platform The mobile platform such as ios or android
     * @param org The name of organization
     *
     * @return List of items
     *
     * @exception IOException Error while accessing the network
     */
    suspend fun getRepos(platform: String, org: String): List<Item> {
        val request = api.searchRepos("${platform}+org:${org}")

        if (!request.isSuccessful) {
            throw IOException("Something went wrong: $request")
        }

        val response = request.body()?.items ?: emptyList()
        response.forEach {
            Log.d("Response", it.toString())
        }
        return response
    }
}