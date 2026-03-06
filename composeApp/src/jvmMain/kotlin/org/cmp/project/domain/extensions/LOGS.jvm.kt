package org.cmp.project.domain.extensions

actual fun log(message: String) {
    println("CMP_LOG: $message")
}