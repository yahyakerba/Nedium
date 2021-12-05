package gh.cloneconf.nedium.screens.search.posts

import androidx.paging.PagingSource
import androidx.paging.PagingState
import gh.cloneconf.extractor.dao.SearchPostDao
import gh.cloneconf.extractor.model.Post
import gh.cloneconf.extractor.model.paging.PostsPaging
import gh.cloneconf.nedium.Singleton.extractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPagingSource(
    private val q : String
) : PagingSource<SearchPostDao.PayloadDao.PagingDao.NextDao, Post>() {

    override val keyReuseSupported = true

    override fun getRefreshKey(state: PagingState<SearchPostDao.PayloadDao.PagingDao.NextDao, Post>): SearchPostDao.PayloadDao.PagingDao.NextDao? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            println(anchorPage?.nextKey)
            anchorPage?.prevKey ?: anchorPage?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<SearchPostDao.PayloadDao.PagingDao.NextDao>): LoadResult<SearchPostDao.PayloadDao.PagingDao.NextDao, Post> {

        var response: PostsPaging
        withContext(Dispatchers.IO) {
            response = extractor.searchForPosts(q, next = params.key)
        }

        return LoadResult.Page(
            data = response.posts,
            prevKey = null,
            nextKey = response.nextDao!!
        )
    }

}
