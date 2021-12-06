package gh.cloneconf.nedium.screens.search.posts

import androidx.paging.PagingSource
import androidx.paging.PagingState
import gh.cloneconf.extractor.dao.SearchPostDto
import gh.cloneconf.extractor.model.PostInfo
import gh.cloneconf.extractor.model.paging.PostsPaging
import gh.cloneconf.nedium.Singleton.extractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

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
                response = extractor.searchForPosts(q, next = params.key)
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
