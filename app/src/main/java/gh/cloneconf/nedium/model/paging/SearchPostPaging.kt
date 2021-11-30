package gh.cloneconf.nedium.model.paging

import gh.cloneconf.nedium.model.Post
import gh.cloneconf.nedium.scrap.search.MediumSearch
import gh.cloneconf.nedium.scrap.search.dao.SearchPostDao

class SearchPostPaging(
    private val q : String,
    posts: List<Post>,
    more : Boolean,
    val nextObj : SearchPostDao.PayloadDao.PagingDao.NextDao? = null,
) : Paging<Post>(posts, more) {
    fun next() = MediumSearch.posts(q, nextObj)
}