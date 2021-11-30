package gh.cloneconf.nedium.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.PostScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gh.cloneconf.nedium.Singleton.gson
import gh.cloneconf.nedium.Singleton.okhttp
import gh.cloneconf.nedium.model.Post
import gh.cloneconf.nedium.scrap.search.MediumSearch
import gh.cloneconf.nedium.scrap.search.dao.SearchPostDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.Request

@Destination
@Composable
fun SearchScreen(navigator: DestinationsNavigator) {
    Scaffold scaffold@ {

        // The query/input.
        var q by rememberSaveable { mutableStateOf("") }

        val scope = rememberCoroutineScope()

        var mPosts by rememberSaveable { mutableStateOf(listOf<Post>()) }

        var mMore by rememberSaveable { mutableStateOf(false) }

        var job by remember { mutableStateOf<Job?>(null) }

        var busy by rememberSaveable { mutableStateOf(false) }

        var nextDao by remember { mutableStateOf<SearchPostDao.PayloadDao.PagingDao.NextDao?>(null) }


        val listState = rememberLazyListState()


        if (mMore) {
            if (!busy && listState.layoutInfo.totalItemsCount > 1) {

                if (listState.layoutInfo.visibleItemsInfo.last().index == mPosts.size) {
                    job = scope.launch(Dispatchers.IO) {
                        busy = true
                        MediumSearch.posts(q, nextDao).apply {
                            withContext(Dispatchers.Main) {
                                mPosts = mPosts.plus(items)
                                mMore = more
                            }
                            busy = false
                        }
                    }
                }

            }
        }


        Column {


                OutlinedTextField(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    value = q,
                    onValueChange = {
                        q = it

                        job?.cancel()
                        job = scope.launch(Dispatchers.IO) {

                            job!!.invokeOnCompletion {
                                busy = false
                            }

                            busy = true
                            MediumSearch.posts(q).apply {
                                withContext(Dispatchers.Main) {
                                    mPosts = items
                                    mMore = more
                                    nextDao = nextObj

                                    if (listState.firstVisibleItemIndex != 0) listState.scrollToItem(
                                        0
                                    )
                                }
                                busy = false
                            }
                        }

                    }
                )



            LazyColumn(state = listState) {
                items(mPosts.size) { i ->

                    val post = mPosts[i]

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .clickable {
                                navigator.navigate(PostScreenDestination(post.id))
                            }
                    ) {
                        Text(post.title, color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)
                        Text(post.desc)
                    }
                }

                if (mMore) item { CircularProgressIndicator(Modifier.padding(20.dp)) }


            }



        }


    }
}

