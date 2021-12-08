package gh.cloneconf.nedium

import com.apollographql.apollo.ApolloClient
import com.google.gson.Gson
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Objects that shared in the whole app.
 */
object Singleton {

    val gson : Gson by lazy { Gson() }

    val okhttp : OkHttpClient by lazy {
        OkHttpClient.Builder()
            .followRedirects(false)
            .followSslRedirects(false)
            .retryOnConnectionFailure(false)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    val apollo : ApolloClient by lazy {
        ApolloClient.builder()
            .serverUrl(Const.MEDIUM_GRAPHQL_ENDPOINT)
            .okHttpClient(okhttp)
            .build()
    }

}