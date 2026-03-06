package org.cmp.project.onboarding

import platform.Foundation.NSUserDefaults

private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"

actual fun isOnboardingCompleted(): Boolean {
    return NSUserDefaults.standardUserDefaults.boolForKey(KEY_ONBOARDING_COMPLETED)
}

actual fun setOnboardingCompleted() {
    NSUserDefaults.standardUserDefaults.setBool(true, KEY_ONBOARDING_COMPLETED)
}
