package org.cmp.project.onboarding

import org.cmp.project.AndroidContext

private const val PREFS_NAME = "cmp_app_prefs"
private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"

actual fun isOnboardingCompleted(): Boolean {
    val prefs = AndroidContext.appContext.getSharedPreferences(PREFS_NAME, android.content.Context.MODE_PRIVATE)
    return prefs.getBoolean(KEY_ONBOARDING_COMPLETED, false)
}

actual fun setOnboardingCompleted() {
    val prefs = AndroidContext.appContext.getSharedPreferences(PREFS_NAME, android.content.Context.MODE_PRIVATE)
    prefs.edit().putBoolean(KEY_ONBOARDING_COMPLETED, true).apply()
}
