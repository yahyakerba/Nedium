package gh.cloneconf.nedium.comps

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

@Composable
fun CoilImage (
    url : String
){

    val painter = rememberImagePainter(
        data = url,
        imageLoader = ImageLoader.Builder(LocalContext.current)
            .componentRegistry {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder(LocalContext.current))
                } else {
                    add(GifDecoder())
                }
            }
            .build()
    )

    Image(painter = painter, contentDescription = null,)

}