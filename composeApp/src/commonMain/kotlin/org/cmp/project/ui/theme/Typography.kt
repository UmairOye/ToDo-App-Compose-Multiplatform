package org.cmp.project.ui.theme

import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.cmp.project.resources.Res
import org.cmp.project.resources.roboto_bold
import org.cmp.project.resources.roboto_medium
import org.cmp.project.resources.roboto_regular
import org.cmp.project.resources.roboto_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun roboto() = FontFamily(
    Font(Res.font.roboto_bold, FontWeight.Bold),
    Font(Res.font.roboto_medium, FontWeight.Medium),
    Font(Res.font.roboto_regular, FontWeight.Normal),
    Font(Res.font.roboto_semibold, FontWeight.SemiBold)
)


@Composable
fun robotoTypography() = androidx.compose.material3.Typography().run {
    val fontFamily = roboto()
    Typography(
        displayLarge = TextStyle(
            fontFamily = fontFamily,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        ),

        titleMedium = TextStyle(
            fontFamily = fontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        ),
        bodyLarge = TextStyle(
            fontFamily = fontFamily,
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Normal
        ),

        bodySmall = TextStyle(
            fontFamily = fontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        ),

        labelLarge = TextStyle(
            fontFamily = fontFamily,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )

    )
}

