package org.cmp.project.settings

import org.cmp.project.AndroidContext

private const val PREFS_NAME = "cmp_app_prefs"
private const val KEY_DARK_THEME = "dark_theme"

actual fun getDarkTheme(): Boolean {
    val prefs = AndroidContext.appContext.getSharedPreferences(PREFS_NAME, android.content.Context.MODE_PRIVATE)
    return prefs.getBoolean(KEY_DARK_THEME, false)
}

actual fun setDarkTheme(dark: Boolean) {
    val prefs = AndroidContext.appContext.getSharedPreferences(PREFS_NAME, android.content.Context.MODE_PRIVATE)
    prefs.edit().putBoolean(KEY_DARK_THEME, dark).apply()
}
