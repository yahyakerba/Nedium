package gh.cloneconf.nedium.scrap.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import gh.cloneconf.nedium.model.Post
import gh.cloneconf.nedium.model.paging.SearchPostPaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPagingSource(
    private val q : String
) : PagingSource<Int, Post>() {
    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        val position = params.key ?: 1

        var response: SearchPostPaging
        withContext(Dispatchers.IO) { response = MediumSearch.posts(q, position) }

        return LoadResult.Page(
            data = response.items,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (response.more) position + 1 else null
        )
    }
}