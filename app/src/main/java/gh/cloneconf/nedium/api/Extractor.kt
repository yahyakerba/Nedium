package gh.cloneconf.nedium.api

import com.apollographql.apollo.coroutines.await
import com.medium.PostQuery
import gh.cloneconf.nedium.api.dao.SearchPostDto
import gh.cloneconf.nedium.model.PostInfo
import gh.cloneconf.nedium.model.paging.PostsPaging
import gh.cloneconf.nedium.Const.USER_AGENT
import gh.cloneconf.nedium.Singleton.apollo
import gh.cloneconf.nedium.Singleton.gson
import gh.cloneconf.nedium.Singleton.okhttp
import okhttp3.FormBody
import okhttp3.Request

object Extractor {

    fun getPostId(url : String) = Regex("([0-9a-f]+)\$").find(url)?.value



    /**
     * Get a post by id.
     */
    suspend fun postById(id : String) = apollo.query(PostQuery(id)).await().data!!.post()


    /**
     * Search for posts.
     *
     * @param q The query you want to search.
     * @param page The page number. Not recommended since pages relay on previous page's [next] object,
     * thought it's possible.
     * @param next The next page information so we can get it.
     */
    fun searchForPosts(
        q : String,
        page : Int = 1,
        next : SearchPostDto.PayloadDto.PagingDto.NextDto? = null
    ): PostsPaging {

        // Building body.
        val body = FormBody.Builder().apply {
            add("q", q)
            next?.apply {
                add("ignoredIds", gson.toJson(ignoredIds))
                add("page", this.page.toString())
                add("pageSize", pageSize.toString())
            } ?: run {
                add("page", page.toString())
            }
        }.build()


        // Request.
        val req = Request.Builder()
            .post(body)
            .url("https://medium.com/search/posts")
            .header("User-Agent", USER_AGENT)
            .header("Accept", "application/json")
            .header("x-xsrf-token", 1.toString())
            .build()

        val resp = okhttp.newCall(req).execute().body()!!.source()


        // Parsing.
        gson.fromJson(
            resp.inputStream().apply {
                skip("])}while(1);</x>".length.toLong())
            }.reader(),
            SearchPostDto::class.java
        ).apply {
            return PostsPaging(
                page = page,
                posts = List(payload.value.size) { i ->
                    val item = payload.value[i]
                    PostInfo(item.id, item.title, item.previewContent.subtitle)
                },
                more = payload.paging.next != null,
                nextDao = payload.paging.next
            )
        }


    }

}