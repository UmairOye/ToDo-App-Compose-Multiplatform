package org.cmp.project.settings

import platform.Foundation.NSUserDefaults

private const val KEY_DARK_THEME = "dark_theme"

actual fun getDarkTheme(): Boolean {
    return NSUserDefaults.standardUserDefaults.boolForKey(KEY_DARK_THEME)
}

actual fun setDarkTheme(dark: Boolean) {
    NSUserDefaults.standardUserDefaults.setBool(dark, KEY_DARK_THEME)
}
