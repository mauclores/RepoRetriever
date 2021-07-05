# RepoRetriever

Library that returns a list of GitHub Repository information given a platform and organization.

# Environment

  - Android studio v4.2

## Using in your project

1. Add the dependency to your main app build.gradle
   ```
   implementation project(':reporetriever')
   ```
1. Create an instance of RepoRetriever and initialize
   ```
   private lateinit var repoRetriever: RepoRetriever
   ....
   repoRetriever = RepoRetriever()
   ```
1. Call API and pass platform and organization
   ```
   repoRetriever.getRepos("android","rakutentech")
   ```

## Testing

1. A sample app is available to call the library, however UI is not provided.
   Check the output logs via Android Studio. Filter by `reporetriever` string.
1. Unit Tests can also be run. See `RepoRetrieverTest`.

## Libraries Used

  - [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
  - [Retrofit](https://square.github.io/retrofit/)
  - [OKHTTPLoggingInterceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
  - [Gson Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson)
  - [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
