package gh.cloneconf.nedium.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.navigation.NavHostController
import com.apollographql.apollo.coroutines.await
import com.medium.PostQuery
import com.ramcosta.composedestinations.annotation.Destination
import gh.cloneconf.nedium.Singleton.apollo

@Destination
@Composable
fun PostScreen(navController: NavHostController, id: String) {


    Scaffold {

        val post by produceState<PostQuery.Post?>(initialValue = null) {
            value = apollo.query(PostQuery(id)).await().data?.post()
        }


        post?.let { post ->

            LazyColumn {

                item {
                    Text(post.title()!!)
                }
            }

        } ?: run {

            CircularProgressIndicator()

        }



    }


}