package gh.cloneconf.nedium.api.suggestions

import com.google.gson.reflect.TypeToken
import gh.cloneconf.nedium.Const
import gh.cloneconf.nedium.Singleton
import gh.cloneconf.nedium.Singleton.okhttp
import okhttp3.Request

class GoogleEngine : SearchEngineBase("Google") {

    override fun suggestions(q: String): List<String> {
        val req = Request.Builder()
            .header("User-Agent", Const.USER_AGENT)
            .url("https://google.com/complete/search?client=chrome&q=$q")
            .build()

        return Singleton.gson.fromJson<List<Any>>(
            okhttp.newCall(req).execute().body()!!.string().apply {
                println(this)
            },
            object : TypeToken<List<Any>>() {}.type
        )[1] as List<String>
    }

}