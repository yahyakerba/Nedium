package gh.cloneconf.nedium

import androidx.compose.material.Colors
import androidx.datastore.preferences.core.intPreferencesKey
import gh.cloneconf.nedium.ui.theme.BlackColorPalette
import gh.cloneconf.nedium.ui.theme.DarkColorPalette
import gh.cloneconf.nedium.ui.theme.LightColorPalette


data class ThemeInfo(
    val nameRes : Int,
    val isDark : Boolean? = false,
    val themeRes : Int? = null,
    val themeColors : (() -> Colors)? = null
)

object Const {

    const val MEDIUM_GRAPHQL_ENDPOINT = "https://medium.com/_/graphql"
    const val USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36"
    const val REPO_LINK = "https://github.com/cloneconf/Nedium"


    val THEME_KEY = intPreferencesKey("theme")

    val themes = listOf(
        ThemeInfo(R.string.follow_system),
        ThemeInfo(
            R.string.light,
            isDark = false,
            themeRes = R.style.Theme_Nedium_Light,
            themeColors = { LightColorPalette }
        ),

        ThemeInfo(
            R.string.dark,
            isDark = true,
            themeRes = R.style.Theme_Nedium_Dark,
            themeColors = { DarkColorPalette }
        ),

        ThemeInfo(
            R.string.black,
            isDark = true,
            themeRes = R.style.Theme_Nedium_Black,
            themeColors = { BlackColorPalette }
        )

    )
}