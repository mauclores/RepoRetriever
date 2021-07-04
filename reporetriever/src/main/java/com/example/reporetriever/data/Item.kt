package com.example.reporetriever.data

/**
 * Data class representing an item in Github Search API.
 * The response has many fields, store only the following.
 */
data class Item(
    val name : String?,
    val private : Boolean?,
    val description : String?,
    val language : String?
)
