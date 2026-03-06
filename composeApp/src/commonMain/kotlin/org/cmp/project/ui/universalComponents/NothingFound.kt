package org.cmp.project.ui.universalComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.cmp.project.ui.theme.roboto

@Composable
@Preview
fun NothingFound(modifier: Modifier = Modifier){


    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp)) {


        Text(
            text = "Nothing Found!",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displayLarge
        )


        Text(
            text = "No notes match your search. Try checking your spelling, or search using a different keyword. You can also create a new note to get started!",
            color = Color.Gray,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 5.dp),
            textAlign = TextAlign.Center,

        )



    }

}