package gh.cloneconf.nedium.screens

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.CachePolicy
import com.medium.PostQuery
import com.medium.type.ParagraphType
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import gh.cloneconf.nedium.R
import gh.cloneconf.nedium.api.Extractor.getPostId
import gh.cloneconf.nedium.api.Extractor.postById

@Destination(
    deepLinks = [
        DeepLink(uriPattern = "https://medium.com/{url}")
    ]
)
@Composable
fun PostScreen(url: String) {

    val id = getPostId(url)!!

    Scaffold {

        var err by rememberSaveable {
            mutableStateOf<Exception?>(null)
        }


        val post by produceState<PostQuery.Post?>(initialValue = null) {
            try {
                value = postById(id)
            } catch (e: Exception) {
                err = e
            }
        }



        if (err != null) {

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    "An error occur",
                    Modifier.padding(0.dp, 20.dp),
                    style = MaterialTheme.typography.h1
                )

                err?.localizedMessage?.also {
                    Text(it, Modifier.padding(10.dp), style = MaterialTheme.typography.caption)
                }

                Text("Please try again later :/")
            }

        } else {


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


                            // Paragraph
                            ParagraphType.P -> {
                                Text(para.text()!!, Modifier.padding(20.dp, 10.dp))
                            }


                            // <li>
                            ParagraphType.ULI, ParagraphType.OLI -> {
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
                                Text(
                                    para.text()!!,
                                    Modifier.padding(20.dp),
                                    fontStyle = FontStyle.Italic,
                                    color = Color.LightGray,
                                    style = MaterialTheme.typography.h1,
                                    fontFamily = FontFamily.Serif
                                )
                            }


                            // Image
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
                                            Modifier.padding(10.dp),
                                            fontStyle = FontStyle.Italic,
                                            color = Color.DarkGray
                                        )
                                    }
                                }

                            }


                            ParagraphType.PRE -> {
                                Box(
                                    modifier = Modifier
                                        .padding(20.dp)
                                        .fillMaxWidth()
                                        .background(Color.DarkGray)
                                ) {
                                    Text(para.text()!!, Modifier.padding(10.dp))
                                }
                            }


                            else -> {
                            }
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


}