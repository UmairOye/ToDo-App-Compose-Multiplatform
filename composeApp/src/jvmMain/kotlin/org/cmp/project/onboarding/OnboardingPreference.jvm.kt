package org.cmp.project.onboarding

import java.util.Properties
import java.io.File

private val prefsFile: File by lazy {
    File(System.getProperty("user.home"), ".cmp_notes_prefs.properties").also {
        if (!it.exists()) it.createNewFile()
    }
}

private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"

actual fun isOnboardingCompleted(): Boolean {
    return runCatching {
        Properties().apply { prefsFile.inputStream().use { load(it) } }
            .getProperty(KEY_ONBOARDING_COMPLETED, "false") == "true"
    }.getOrDefault(false)
}

actual fun setOnboardingCompleted() {
    val props = Properties().apply { prefsFile.takeIf { f -> f.exists() && f.length() > 0 }?.inputStream()?.use { load(it) } }
    props[KEY_ONBOARDING_COMPLETED] = "true"
    prefsFile.outputStream().use { props.store(it, null) }
}
