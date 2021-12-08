package gh.cloneconf.nedium.api.suggestions

import gh.cloneconf.nedium.Const.USER_AGENT
import gh.cloneconf.nedium.Singleton.gson
import gh.cloneconf.nedium.Singleton.okhttp
import okhttp3.Request

class QwantEngine : SearchEngineBase("Qwant") {


    data class SuggestionResponseDto (
        val `data`: DataDto,
//        val status: String
    ) {
        data class DataDto(
            val items: List<ItemDto>,
//            val special: List<Any>
        ) {
            data class ItemDto(
//                val suggestType: Int,
                val value: String
            )
        }
    }

    override fun suggestions(q: String): List<String> {
        val req = Request.Builder()
            .header("User-Agent", USER_AGENT)
            .url("https://api.qwant.com/v3/suggest?q=$q&locale=de_DE")
            .build()

        gson.fromJson(
            okhttp.newCall(req).execute().body()!!.source().inputStream().reader(),
            SuggestionResponseDto::class.java
        ).data.items.apply {

            return List(size) {
                get(it).value
            }

        }
    }
}