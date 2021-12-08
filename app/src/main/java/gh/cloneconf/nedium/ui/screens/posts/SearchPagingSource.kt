package gh.cloneconf.nedium.ui.screens.posts

import androidx.paging.PagingSource
import androidx.paging.PagingState
import gh.cloneconf.nedium.api.Extractor.searchForPosts
import gh.cloneconf.nedium.api.dao.SearchPostDto
import gh.cloneconf.nedium.model.PostInfo
import gh.cloneconf.nedium.model.paging.PostsPaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPagingSource(
    private val q: String
) : PagingSource<SearchPostDto.PayloadDto.PagingDto.NextDto, PostInfo>() {

    override val keyReuseSupported = true

    override fun getRefreshKey(state: PagingState<SearchPostDto.PayloadDto.PagingDto.NextDto, PostInfo>): SearchPostDto.PayloadDto.PagingDto.NextDto? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            println(anchorPage?.nextKey)
            anchorPage?.prevKey ?: anchorPage?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<SearchPostDto.PayloadDto.PagingDto.NextDto>): LoadResult<SearchPostDto.PayloadDto.PagingDto.NextDto, PostInfo> {

        var response: PostsPaging
        return withContext(Dispatchers.IO) {
            try {
                response = searchForPosts(q, next = params.key)
                return@withContext LoadResult.Page(
                    data = response.posts,
                    prevKey = null,
                    nextKey = response.nextDao
                )
            }catch (e:Exception){
                LoadResult.Error(e)
            }
        }
    }

}
