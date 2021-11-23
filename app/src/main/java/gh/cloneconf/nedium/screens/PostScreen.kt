package gh.cloneconf.nedium.screens

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.apollographql.apollo.coroutines.await
import com.medium.PostQuery
import com.medium.type.ParagraphType
import com.skydoves.landscapist.coil.CoilImage
import gh.cloneconf.nedium.Singleton.apollo


@Composable
fun PostScreen(id : String) {

    Scaffold {

        var error by rememberSaveable { mutableStateOf<Exception?>(null) }

        // Getting the post
        val post by produceState<PostQuery.Post?>(initialValue = null, producer = {
            try {
                apollo.query(PostQuery(id)).await().data!!.post()!!.apply {
                    value = this
                }
            }catch (e:Exception)  {
                error = e
            }
        })

        val listState = rememberLazyListState()

        if (error != null) {

            Box(modifier = Modifier.fillMaxSize()) {
                Text("Error :/", style = MaterialTheme.typography.h1)
                Text(error!!.localizedMessage!!, Modifier.align(Alignment.Center))
            }


        }else{

            if (post == null) {

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            } else {


                LazyColumn(state = listState) {


                    // The author information.
                    item {

                        Text(
                            "by ${post!!.creator()!!.name()!!}",
                            color = Color.Gray,
                            modifier = Modifier.padding(10.dp)
                        )

                    }


                    // Post title.
                    item {
                        Text(
                            post!!.title()!!,
                            Modifier.padding(10.dp, 10.dp, 0.dp, 10.dp),
                            style = MaterialTheme.typography.h1,
                        )
                    }


                    val paras = post!!.content()!!.bodyModel()!!.paragraphs()!!

                    // Printing the content..
                    items(paras.size) { i ->

                        val para = paras[i]

                        when (para.type()) {


                            // Paragraph
                            ParagraphType.P -> {
                                Text(para.text()!!, Modifier.padding(10.dp, 10.dp, 0.dp, 10.dp))
                            }


                            // Headers
                            ParagraphType.H2 -> {
                                Text(
                                    text = para.text()!!,
                                    Modifier.padding(10.dp, 10.dp, 0.dp, 10.dp),
                                    style = MaterialTheme.typography.h3
                                )
                            }

                            ParagraphType.H3 -> {
                                Text(
                                    text = para.text()!!,
                                    Modifier.padding(10.dp, 10.dp, 0.dp, 10.dp),
                                    style = MaterialTheme.typography.h3
                                )
                            }

                            ParagraphType.H4 -> {
                                Text(
                                    text = para.text()!!,
                                    Modifier.padding(10.dp, 10.dp, 0.dp, 10.dp),
                                    style = MaterialTheme.typography.h4
                                )
                            }


                            // List item
                            ParagraphType.ULI -> {
                                Row(Modifier.padding(10.dp, 10.dp, 0.dp, 10.dp)) {
                                    Box(
                                        modifier = Modifier
                                            .size(10.dp, 2.dp)
                                            .background(Color.Gray)
                                            .clip(RoundedCornerShape(50))
                                            .align(Alignment.CenterVertically)
                                    )
                                    Text(
                                        text = para.text()!!,
                                        Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
                                    )
                                }
                            }


                            // Pre / <pre>
                            ParagraphType.PRE -> {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp)
                                        .background(Color.DarkGray)
                                ) {
                                    Text(para.text()!!, Modifier.padding(20.dp))
                                }
                            }


                            // Image
                            ParagraphType.IMG -> {

                                val metadata = para.metadata()!!
                                val width = metadata.originalWidth() ?: 800
                                val height = metadata.originalHeight() ?: 600

                                val url =
                                    "https://cdn-images-1.medium.com/fit/c/$width/$height/${metadata.id()}"

                                Box(modifier = Modifier.fillMaxWidth()) {

                                    val context = LocalContext.current
                                    val imageLoader by remember {
                                        mutableStateOf(
                                        ImageLoader.Builder(context)
                                            .componentRegistry {
                                                if (SDK_INT >= 28) {
                                                    add(ImageDecoderDecoder(context))
                                                } else {
                                                    add(GifDecoder())
                                                }
                                            }
                                            .build()
                                        )
                                    }

                                    Box(
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(0.dp, 10.dp, 0.dp, 10.dp)
                                    ) {
                                        CoilImage(
                                            imageModel = url, // URL of the animated images.
                                            imageLoader = { imageLoader },
                                            loading = {
                                                Box(Modifier.fillMaxSize()) {
                                                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                                                }
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            contentScale = ContentScale.Fit
                                        )

                                        if (!para?.text().isNullOrEmpty()) {
                                            Text(
                                                para.text()!!,
                                                Modifier
                                                    .padding(5.dp)
                                                    .background(Color.Black)
                                                    .align(Alignment.BottomStart),
                                                style = MaterialTheme.typography.caption,
                                                color = Color.White
                                            )
                                        }
                                    }

                                }


                            }

                        }

                    }


                }
            }

        }



    }

}