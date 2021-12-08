package gh.cloneconf.nedium

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberDestinationsNavController
import gh.cloneconf.nedium.ui.theme.NediumTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NediumTheme {
                val navController = rememberDestinationsNavController()
                DestinationsNavHost(navController = navController)
            }
        }

    }

}