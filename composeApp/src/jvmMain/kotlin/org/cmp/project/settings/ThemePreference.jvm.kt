package org.cmp.project.settings

import java.util.Properties
import java.io.File

private val prefsFile: File by lazy {
    File(System.getProperty("user.home"), ".cmp_notes_prefs.properties").also {
        if (!it.exists()) it.createNewFile()
    }
}

private const val KEY_DARK_THEME = "dark_theme"

actual fun getDarkTheme(): Boolean {
    return runCatching {
        val props = Properties()
        prefsFile.takeIf { it.exists() && it.length() > 0 }?.inputStream()?.use { props.load(it) }
        props.getProperty(KEY_DARK_THEME, "false") == "true"
    }.getOrDefault(false)
}

actual fun setDarkTheme(dark: Boolean) {
    val props = Properties().apply { prefsFile.takeIf { f -> f.exists() && f.length() > 0 }?.inputStream()?.use { load(it) } }
    props[KEY_DARK_THEME] = dark.toString()
    prefsFile.outputStream().use { props.store(it, null) }
}
