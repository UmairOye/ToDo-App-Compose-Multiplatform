package org.cmp.project.data.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.cmp.project.AndroidContext


private var driverInstance: SqlDriver? = null


actual fun createDriver(): SqlDriver {

    if (driverInstance == null) {
        driverInstance = AndroidSqliteDriver(
            NotesDatabase.Schema,
            AndroidContext.appContext,
            "notes.db"
        )
    }
    return driverInstance!!
}
