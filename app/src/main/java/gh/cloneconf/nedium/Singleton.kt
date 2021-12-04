package gh.cloneconf.nedium

import gh.cloneconf.extractor.Extractor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object Singleton {

    const val MEDIUM_ENDPOINT = "https://medium.com/_/graphql"

    val okhttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .followRedirects(false)
            .followSslRedirects(false)
            .retryOnConnectionFailure(false)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    val extractor by lazy {
        Extractor(okhttp)
    }

}