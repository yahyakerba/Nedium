package gh.cloneconf.nedium

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

object Singleton {

    const val MEDIUM_ENDPOINT = "https://medium.com/_/graphql"

    val okhttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .followRedirects(false)
            .followSslRedirects(false)
            .retryOnConnectionFailure(false)
            .build()
    }

    val apollo: ApolloClient by lazy {
        ApolloClient.builder()
            .serverUrl(MEDIUM_ENDPOINT)
            .okHttpClient(okhttp)
            .build()
    }

}