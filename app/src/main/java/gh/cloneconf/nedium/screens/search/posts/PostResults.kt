package gh.cloneconf.nedium.screens.search.posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingConfig.Companion.MAX_SIZE_UNBOUNDED
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed

@Composable
fun PostResults(
    q : String,
    onPostClicked : (id : String) -> Unit
) {

    Scaffold {

        if (q.isEmpty()) {
            Box(Modifier.padding(20.dp), contentAlignment = Alignment.Center) {
                Text("No query passed!")
            }
            return@Scaffold
        }

        val pager = remember {
            Pager(
                PagingConfig(
                    pageSize = 10,
                    maxSize = 100,
                    prefetchDistance = 1,
                )
            ) { SearchPagingSource(q) }
        }

        val lazyPagingItems = pager.flow.collectAsLazyPagingItems()

        LazyColumn(Modifier.fillMaxSize()) {

            if (lazyPagingItems.loadState.refresh == LoadState.Loading) item {
                CircularProgressIndicator(
                    Modifier.padding(20.dp)
                )
            }

            itemsIndexed(lazyPagingItems) { _, post ->
                post?.apply {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .clickable { onPostClicked.invoke(id) }
                    ) {

                        Text(
                            title,
                            Modifier.padding(20.dp, 20.dp, 20.dp, 5.dp),
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.primary
                        )

                        if (desc.isNotEmpty()) Text(
                                desc,
                                Modifier.padding(20.dp, 5.dp, 20.dp, 20.dp)
                            )
                    }
                }
            }

            if (lazyPagingItems.loadState.append == LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
        }


    }
}