package gh.cloneconf.nedium.scrap.search

import gh.cloneconf.nedium.Singleton.gson
import gh.cloneconf.nedium.Singleton.okhttp
import gh.cloneconf.nedium.model.Post
import gh.cloneconf.nedium.model.paging.SearchPostPaging
import gh.cloneconf.nedium.scrap.search.dao.SearchPostDao
import okhttp3.FormBody
import okhttp3.Request

object MediumSearch {

    fun posts(q : String, page: Int = 1) : SearchPostPaging {
        val form = FormBody.Builder()
            .add("q", q)
            .add("page", page.toString())


        val req = Request.Builder()
            .post(form.build())
            .url("https://medium.com/search/posts")
            .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36")
            .header("Accept", "application/json")
            .header("x-xsrf-token", 1.toString())
            .build()

        val resp = okhttp.newCall(req).execute()

        val obj = gson.fromJson(
            resp.body()!!.string().replaceFirst("])}while(1);</x>", "").apply {
                                                                              println(this)
            },
            SearchPostDao::class.java
        )

        return SearchPostPaging(
            q,
            posts = ArrayList<Post>().apply {
                for (v in obj.payload.value) {
                    add(Post(
                        id = v.id,
                        title = v.title,
                        desc = v.previewContent.subtitle
                    ))
                }
            },
            more = obj.payload.paging.next != null,
            nextObj = obj.payload.paging.next
        )
    }

}