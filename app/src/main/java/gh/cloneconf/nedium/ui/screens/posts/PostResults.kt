package gh.cloneconf.nedium.ui.screens.posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.ramcosta.composedestinations.annotation.Destination
import gh.cloneconf.nedium.ui.parts.ErrorComp

@Destination
@Composable
fun PostResults(
    q: String,
    onPostClicked: (id: String) -> Unit
) {
    if (q.isEmpty()) return
    val viewModel = viewModel<PostsViewModel>()


    Scaffold {

        val pager = remember(q) {
            viewModel.apply {
                if (a == null) {
                    a = Pager(
                        PagingConfig(
                            pageSize = 10,
                            maxSize = 100,
                            prefetchDistance = 3,
                        )
                    ) { SearchPagingSource(q) }
                }
            }.a!!
        }



        val lazyPagingItems =
            pager.flow.collectAsLazyPagingItems()

        val a =Pager(
            PagingConfig(
                pageSize = 10,
                maxSize = 100,
                prefetchDistance = 3,
            )
        ) { SearchPagingSource(q) }

        val lazyListState = rememberLazyListState()

        LazyColumn(
            Modifier.fillMaxSize(),
            state = lazyListState
        ) {

            if (lazyPagingItems.loadState.refresh == LoadState.Loading) item {
                LinearProgressIndicator(
                    Modifier.fillMaxWidth()
                )
            }else if (lazyPagingItems.loadState.refresh is LoadState.Error) {
                val a = lazyPagingItems.loadState.refresh as LoadState.Error
                item { ErrorComp(a.error) { lazyPagingItems.retry() } }
            }


            itemsIndexed(lazyPagingItems) { _, post ->
                post?.apply {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                onPostClicked.invoke(id)
                            }
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
                        else Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp))
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
            }else if (lazyPagingItems.loadState.append is LoadState.Error) {
                val a = lazyPagingItems.loadState.append as LoadState.Error
                item {
                    ErrorComp(a.error) { lazyPagingItems.retry() }
                }
            }

            item {
                Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 50.dp))
            }

        }


    }
}