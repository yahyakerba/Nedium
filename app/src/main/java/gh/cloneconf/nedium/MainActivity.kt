package gh.cloneconf.nedium

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberDestinationsNavController
import gh.cloneconf.nedium.ui.theme.NediumTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController : NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent?.dataString ?: intent.getStringExtra(Intent.EXTRA_TEXT)

        setContent {

            NediumTheme(darkTheme = true) {
                val navController = rememberDestinationsNavController()
                DestinationsNavHost(navController = navController)
            }

        }

    }

}