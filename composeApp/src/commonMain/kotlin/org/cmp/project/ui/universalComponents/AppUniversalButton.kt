package org.cmp.project.ui.universalComponents

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppUniversalButton (
    onButtonClick:() -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    modifier: Modifier = Modifier,
    actionName: String = "Action Name",
    contentPaddingValues: PaddingValues = PaddingValues(all = 12.dp)
){


    Button(
        contentPadding = contentPaddingValues,
        modifier = modifier.fillMaxWidth(),
        colors = colors,
        onClick = onButtonClick,
        content = {
            Text(
                text = actionName,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }

    )
}



@Composable
@Preview
fun PreviewAppUniversalButton(){
    AppUniversalButton(onButtonClick = {})
}