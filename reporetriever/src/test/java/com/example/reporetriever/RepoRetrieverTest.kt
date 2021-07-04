package com.example.reporetriever

import com.example.reporetriever.api.GithubApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class RepoRetrieverTest {
    // Class to be tested
    private lateinit var testRepoRetriever: RepoRetriever

    // Mocks
    private lateinit var server: MockWebServer
    private lateinit var api: GithubApi

    @Before
    fun init() {
        server = MockWebServer()
        server.start(8080)

        api = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApi::class.java)

        testRepoRetriever = RepoRetriever(api)
    }

    @After
    fun shutdown() {
        server.shutdown()
    }

    @Test
    fun testReturnWithItems() {
        server.apply {
            enqueue(MockResponse().setBody(
                MockResponseFileReader("githubapi_with_items.json").content))
        }

        val response = runBlocking {
            testRepoRetriever.getRepos("android", "rakutentech")
        }

        assertEquals(2, response.size)

        val item1 = response[0]
        assertEquals("android-buildconfig", item1.name)
        assertEquals(false, item1.private)
        assertEquals("Shared (gradle) build configurations for Android and Java libraries",
            item1.description)
        assertEquals("Groovy", item1.language)

        val item2 = response[1]
        assertEquals("android-perftracking", item2.name)
        assertEquals(false, item2.private)
        assertEquals("Performance Tracking for Android Apps", item2.description)
        assertEquals("Java", item2.language)
    }

    @Test
    fun testReturnEmptyItems() {
        server.apply {
            enqueue(MockResponse().setBody(
                MockResponseFileReader("githubapi_empty_items.json").content))
        }

        val response = runBlocking {
            testRepoRetriever.getRepos("testplatform", "testorg")
        }

        assertTrue(response.isEmpty())
    }

    @Test
    fun testReturnFail() {
        server.apply {
            enqueue(MockResponse().setResponseCode(404))
        }
        assertFailsWith<IOException> {
            runBlocking {
                testRepoRetriever.getRepos("ios", "rakutentech")
            }
        }
    }
}