package org.cmp.project

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.Navigator
import org.cmp.project.settings.getDarkTheme
import org.cmp.project.settings.setDarkTheme
import org.cmp.project.ui.screens.gate.GateScreenContent
import org.cmp.project.ui.theme.AppTheme
import org.cmp.project.ui.theme.LocalThemeState
import org.cmp.project.ui.theme.ThemeState

@Composable
fun App() {
    var darkTheme by remember { mutableStateOf(getDarkTheme()) }
    CompositionLocalProvider(
        LocalThemeState provides ThemeState(
            isDark = darkTheme,
            setDark = { value ->
                setDarkTheme(value)
                darkTheme = value
            }
        )
    ) {
        AppTheme(darkTheme = darkTheme) {
            Navigator(GateScreenContent())
        }
    }
}