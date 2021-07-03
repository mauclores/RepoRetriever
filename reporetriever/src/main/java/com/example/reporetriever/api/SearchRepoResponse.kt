package com.example.reporetriever.api

import com.example.reporetriever.data.Item

data class SearchRepoResponse(
    val items: List<Item>
)