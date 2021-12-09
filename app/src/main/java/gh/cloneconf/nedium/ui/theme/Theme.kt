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
    primary = Red300,
    primaryVariant = Red700,
    secondary = Yellow200,
    surface = Color.Black
)

@Composable
fun NediumTheme(colors : (() -> Colors)?, content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = colors?.invoke()  ?:  if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}