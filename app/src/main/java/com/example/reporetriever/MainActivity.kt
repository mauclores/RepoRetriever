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

        // TODO: Check if there is internet?
        repoRetriever = RepoRetriever()
        lifecycleScope.launch {
            val response = repoRetriever.getRepositories("android", "rakutentech")
            response.items.forEach {
                println(it.toString())
            }
        }
    }
}