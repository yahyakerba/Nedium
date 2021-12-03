package gh.cloneconf.nedium.scrap.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed

@Composable
fun PostResults(
    q : String,
    onPostClicked : (id : String) -> Unit
) {

    if (q.isEmpty()) {
        Box(Modifier.padding(20.dp), contentAlignment = Alignment.Center) {
            Text("No query passed!")
        }
        return
    }


    val lazyPagingItems = Pager(
        PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            maxSize = 100
        )
    ) { SearchPagingSource(q) }.flow
        .collectAsLazyPagingItems()


    LazyColumn(Modifier.fillMaxSize()) {

        if (lazyPagingItems.loadState.refresh == LoadState.Loading) item { CircularProgressIndicator(Modifier.padding(20.dp)) }

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

                    desc?.also {
                        Text(
                            desc,
                            Modifier.padding(20.dp, 5.dp, 20.dp, 20.dp)
                        )
                    }
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