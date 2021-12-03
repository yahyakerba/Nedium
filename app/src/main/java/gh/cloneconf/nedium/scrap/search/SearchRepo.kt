package gh.cloneconf.nedium.scrap.search

import androidx.paging.Pager
import androidx.paging.PagingConfig

class SearchRepo {

    fun getResults(query : String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 30,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { SearchPagingSource(query) }
        )

}