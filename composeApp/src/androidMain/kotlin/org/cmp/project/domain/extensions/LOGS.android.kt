package org.cmp.project.domain.extensions

import android.util.Log

actual fun log(message: String) {
    Log.d("CMP_LOG", message)
}