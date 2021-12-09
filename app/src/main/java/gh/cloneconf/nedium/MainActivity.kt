package gh.cloneconf.nedium

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.PostScreenDestination
import com.ramcosta.composedestinations.rememberDestinationsNavController
import gh.cloneconf.nedium.Const.THEME_KEY
import gh.cloneconf.nedium.Const.themes
import gh.cloneconf.nedium.api.Extractor
import gh.cloneconf.nedium.ui.theme.NediumTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ds = runBlocking { dataStore.data.first() }

        val theme = ds[THEME_KEY]?.run { themes[this] }


        theme?.isDark?.also {

            println(it)

//            AppCompatDelegate.setDefaultNightMode(
//                if (it) MODE_NIGHT_YES
//                else MODE_NIGHT_NO
//            )
        }
        theme?.themeRes?.also { setTheme(it) }






        setContent {
            NediumTheme(theme?.themeColors) {
                navController = rememberDestinationsNavController()
                DestinationsNavHost(navController = navController)

                intent?.apply {

                    if (!navController.handleDeepLink(this)) {
                        dataString ?: getStringExtra(Intent.EXTRA_TEXT)?.also {
                            Extractor.getPostId(it)?.also { id ->
                                navController.navigate(PostScreenDestination(id).route)
                            }
                        }
                    }

                }


            }
        }



    }

}