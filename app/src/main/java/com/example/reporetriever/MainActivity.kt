package com.example.reporetriever

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var repoRetriever: RepoRetriever

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: Check if there is internet?
        repoRetriever = RepoRetriever()
        GlobalScope.launch(Dispatchers.IO) {
            val response = repoRetriever.getRepositories("", "")
            response.items.forEach {
                println(it.toString())
            }
        }
    }
}