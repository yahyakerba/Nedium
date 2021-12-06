package gh.cloneconf.nedium.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkColorPalette = darkColors(
    primary = Red300,
    primaryVariant = Red700,
    secondary = Yellow200
)

val LightColorPalette = lightColors(
    primary = Red300,
    primaryVariant = Red700,
    secondary = Yellow200

    /* Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,
*/
)

val BlackColorPalette = darkColors(
    background = Color.Black,

    onBackground = Color.Black,
    primary = Red300,
    primaryVariant = Red700,
    secondary = Yellow200
)

@Composable
fun NediumTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors ?: if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}