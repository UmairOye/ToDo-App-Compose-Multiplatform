package org.cmp.project

import android.content.Context

object AndroidContext {
    private var _appContext: Context? = null
    val appContext: Context
        get() = _appContext ?: error("AndroidContext not initialized. Call AndroidContext.init(context) in MainActivity.onCreate()")

    fun init(context: Context) {
        _appContext = context.applicationContext
    }
}