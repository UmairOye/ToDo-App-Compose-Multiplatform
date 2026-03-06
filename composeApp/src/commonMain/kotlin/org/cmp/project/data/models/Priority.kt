package org.cmp.project.data.models

import androidx.compose.ui.graphics.Color

enum class Priority(val displayName: String, val color: Color) {
    URGENT("Urgent", Color.Red),
    HIGH("High", Color(0xFFFFA000)),
    NORMAL("Normal", Color(0xFF209203)),
    LOW("Low", Color.Gray)
}
