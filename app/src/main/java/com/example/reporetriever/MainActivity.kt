package com.example.reporetriever

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            try {
                repoRetriever.getRepos("android","rakutentech")
            } catch (e: Exception) {
                Log.e(TAG, "$e")
            }
            // Do something with response here
        }
    }
}