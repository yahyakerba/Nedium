package gh.cloneconf.nedium.api.suggestions

import com.google.gson.reflect.TypeToken
import gh.cloneconf.nedium.Const.USER_AGENT
import gh.cloneconf.nedium.Singleton.gson
import gh.cloneconf.nedium.Singleton.okhttp
import okhttp3.Request

class DuckduckgoEngine : SearchEngineBase("Duckduckgo") {

    override fun suggestions(q: String): List<String> {

        val req = Request.Builder()
            .header("User-Agent", USER_AGENT)
            .url("https://duckduckgo.com/ac/?q=$q&kl=wt-wt")
            .build()

        val resp = okhttp.newCall(req).execute()

        gson.fromJson<List<Map<String, String>>>(
            resp.body()!!.source().inputStream().reader(),
            object : TypeToken<List<Map<String, String>>>() {}.type
        ).apply {

            return List(size) {
                get(it).values.first()
            }
        }

    }
}