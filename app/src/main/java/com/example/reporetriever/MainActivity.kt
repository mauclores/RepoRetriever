package com.example.reporetriever

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var repoRetriever: RepoRetriever

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repoRetriever = RepoRetriever()
        getRepositories()
    }

    private fun getRepositories() {
        lifecycleScope.launch {
            repoRetriever.getRepos("android","rakutentech")
            // Do something with the result here
        }
    }
}