package org.cmp.project.ui.universalComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BackButton (modifier: Modifier = Modifier, screenName: String = "", onClick:() -> Unit){


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Small floating action button.")
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = screenName,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}



@Composable
@Preview
fun PreviewBackButton(){
    BackButton(screenName = "Add Note", onClick = {})
}