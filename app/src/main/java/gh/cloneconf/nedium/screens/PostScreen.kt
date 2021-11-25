package gh.cloneconf.nedium.screens

import android.os.Build
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.CachePolicy
import com.apollographql.apollo.coroutines.await
import com.medium.PostQuery
import com.medium.type.ParagraphType
import gh.cloneconf.nedium.R
import gh.cloneconf.nedium.Singleton.apollo

@ExperimentalCoilApi
@Composable
fun PostScreen(id: String) {


    Scaffold {


        val scope = rememberCoroutineScope()

        val infiniteTransition = rememberInfiniteTransition()


        val post by produceState<PostQuery.Post?>(initialValue = null) {
            value = apollo.query(PostQuery(id)).await().data?.post()
        }


        post?.let { post ->

            LazyColumn {


                val paras = post.content()!!.bodyModel()!!.paragraphs()!!
                items(paras.size) { i ->
                    val para = paras[i]


                    when (para.type()) {

                        // Headers
                        ParagraphType.H2 -> Text(
                            para.text()!!,
                            Modifier.padding(10.dp),
                            style = MaterialTheme.typography.h2
                        )
                        ParagraphType.H3 -> Text(
                            para.text()!!,
                            Modifier.padding(10.dp),
                            style = MaterialTheme.typography.h3
                        )
                        ParagraphType.H4 -> Text(
                            para.text()!!,
                            Modifier.padding(10.dp),
                            style = MaterialTheme.typography.h4
                        )


                        ParagraphType.P -> {
                            Text(para.text()!!, Modifier.padding(10.dp))
                        }


                        ParagraphType.ULI -> {
                            Row(
                                Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    Modifier
                                        .size(10.dp, 3.dp)
                                        .background(Color.DarkGray)
                                )
                                Text(para.text()!!, Modifier.padding(10.dp))
                            }
                        }


                        ParagraphType.PQ -> {
                            Text(para.text()!!, Modifier.padding(20.dp), fontStyle = FontStyle.Italic, color = Color.LightGray, style = MaterialTheme.typography.h1, fontFamily = FontFamily.Serif)
                        }


                        ParagraphType.IMG -> {
                            val metadata = para.metadata()!!

                            val width = metadata.originalWidth() ?: 800
                            val height = metadata.originalWidth() ?: 600

                            val url =
                                "https://cdn-images-1.medium.com/fit/c/$width/$height/${metadata.id()}"

                            val painter = rememberImagePainter(
                                data = url,
                                builder = {
                                    crossfade(true)
                                    memoryCacheKey(metadata.id())
                                    networkCachePolicy(CachePolicy.ENABLED)
                                    placeholder(R.drawable.ic_launcher_foreground)
                                },
                                imageLoader = ImageLoader.Builder(LocalContext.current)
                                    .componentRegistry {
                                        if (Build.VERSION.SDK_INT >= 28) {
                                            add(ImageDecoderDecoder(LocalContext.current))
                                        } else {
                                            add(GifDecoder())
                                        }
                                    }
                                    .build(),

                            )

                            Column(
                                Modifier
                                    .padding(0.dp, 10.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Image(
                                    painter = painter,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(250.dp),
                                    contentScale = ContentScale.Fit
                                )

                                para.text()?.also {
                                    Text(
                                        it,
                                        Modifier.padding(5.dp),
                                        fontStyle = FontStyle.Italic,
                                        color = Color.DarkGray
                                    )
                                }
                            }

                        }



                        ParagraphType.PRE -> {
                            Box(modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth()
                                .background(Color.DarkGray)) {
                                Text(para.text()!!, Modifier.padding(10.dp))
                            }
                        }



                        else -> {  }
                    }
                }


            }

        } ?: run {

            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

        }



    }


}