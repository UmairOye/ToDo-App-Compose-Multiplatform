package org.cmp.project.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightColors: CustomColors = CustomColors(
    whiteColor = Color(0xFFFFFFFF),
    blackColor = Color(0xFF000000),
    backGroundColor = Color(0xFFF0F0F0),
    edBgColor = Color(0xFFDBDBDB),
    edTextColor = Color(0xFFF0F0F0),
    skyBlueColor = Color(0xFF87CEEB)

)


private val DarkColors: CustomColors = CustomColors(
    whiteColor = Color(0xFFE0E0E0),
    blackColor = Color(0xFFE8E8E8),
    backGroundColor = Color(0xFF121212),
    edBgColor = Color(0xFF2D2D2D),
    edTextColor = Color(0xFFB0B0B0),
    skyBlueColor = Color(0xFF64B5F6)
)


val LocalCustomColors = staticCompositionLocalOf {
    LightColors
}

/** Current dark theme and setter; provide in App so Settings can update theme. */
data class ThemeState(val isDark: Boolean, val setDark: (Boolean) -> Unit)
val LocalThemeState = staticCompositionLocalOf<ThemeState> { error("No ThemeState provided") }


@Composable
fun AppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {


    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        typography = robotoTypography()
    ){
        CompositionLocalProvider(
            LocalCustomColors provides colors
        ) {
            content()
        }
    }


}