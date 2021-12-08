package gh.cloneconf.nedium

import gh.cloneconf.nedium.ui.theme.BlackColorPalette
import gh.cloneconf.nedium.ui.theme.DarkColorPalette
import gh.cloneconf.nedium.ui.theme.LightColorPalette


object Const {

    const val MEDIUM_GRAPHQL_ENDPOINT = "https://medium.com/_/graphql"
    const val USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36"
    const val REPO_LINK = "https://github.com/cloneconf/Nedium"


    val themes = listOf(
        R.string.follow_system to Pair( R.style.Theme_Nedium, null ),
        R.string.light to Pair( R.style.Theme_Nedium_Light, { LightColorPalette } ),
        R.string.dark to Pair( R.style.Theme_Nedium_Black, { DarkColorPalette } ),
        R.string.black to { BlackColorPalette },
    )
}