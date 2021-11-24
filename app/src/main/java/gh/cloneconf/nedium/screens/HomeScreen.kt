package gh.cloneconf.nedium.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import gh.cloneconf.nedium.comps.CoilImage

@Composable
fun HomeScreen(navigator: NavController) {
    CoilImage(url = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fmir-s3-cdn-cf.behance.net%2Fproject_modules%2Fmax_1200%2F9c64a974542685.5c33748f6d671.gif&f=1&nofb=1")
}