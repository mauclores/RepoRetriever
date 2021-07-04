package com.example.reporetriever

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var repoRetriever: RepoRetriever

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repoRetriever = RepoRetriever()
        getRepositories()
    }

    private fun getRepositories() {
        repoRetriever.getRepos("android","rakutentech")
    }
}