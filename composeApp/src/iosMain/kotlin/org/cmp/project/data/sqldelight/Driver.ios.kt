package org.cmp.project.data.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual fun createDriver(): SqlDriver {
    return NativeSqliteDriver(NotesDatabase.Schema, "notes.db")
}
