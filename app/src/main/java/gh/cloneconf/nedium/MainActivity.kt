package gh.cloneconf.nedium

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import gh.cloneconf.nedium.screens.HomeScreen
import gh.cloneconf.nedium.screens.PostScreen
import gh.cloneconf.nedium.ui.theme.NediumTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent?.dataString ?: intent.getStringExtra(Intent.EXTRA_TEXT)


        var id : String? = null

        if (url != null) {
            Regex("([0-9a-f]+)\$").find(url)?.value?.let {
                id = it
            } ?: run {
                Toast.makeText(this, "Unable to get the article id!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        setContent {
            NediumTheme(true) {
                id?.let { PostScreen(it) } ?: run { HomeScreen() }
            }
        }

    }


}