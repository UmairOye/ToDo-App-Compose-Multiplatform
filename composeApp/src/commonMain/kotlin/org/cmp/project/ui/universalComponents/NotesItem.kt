package org.cmp.project.ui.universalComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.cmp.project.data.models.Priority
import org.cmp.project.data.sqldelight.NoteEntity

@Composable
fun NotesItem(noteEntity: NoteEntity, onClick:() -> Unit,
              onDelete:() -> Unit){
    val cardColor = generateSoftColorForId(noteEntity.id)

   val color =  when(noteEntity.priority){
        Priority.LOW.displayName -> Priority.LOW.color
        Priority.URGENT.displayName -> Priority.URGENT.color
        Priority.HIGH.displayName -> Priority.HIGH.color
        Priority.NORMAL.displayName -> Priority.NORMAL.color
       else -> Priority.LOW.color
   }
    val dismissState = rememberSwipeToDismissBoxState(
        initialValue = SwipeToDismissBoxValue.Settled,
    )

    LaunchedEffect(dismissState.currentValue) {
        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
            onDelete()
        }
    }

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            Box(
                Modifier.fillMaxSize().padding(all = 3.dp).background(Color.Red,shape = RoundedCornerShape(13.dp)),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(Icons.Default.Delete, null, tint = Color.White)
            }
        },
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true,
    ) {
        Column(modifier = Modifier.background(
            color = cardColor,
            shape = RoundedCornerShape(12.dp)
        )

            .combinedClickable(
                onClick = { onClick() },
                onLongClick = {

                }
            ).padding(all = 12.dp)) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Date: ${noteEntity.reminderDate}",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start)


            Row (modifier = Modifier.padding(top = 3.dp),
                horizontalArrangement = Arrangement.Center){

                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(color = color, CircleShape)
                )

                Text(
                    modifier = Modifier.fillMaxWidth().padding(start = 5.dp),
                    text = noteEntity.priority,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start)
            }


            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = noteEntity.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = noteEntity.content,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )




        }
    }



}


fun generateSoftColorForId(id: Long): Color {
    val hue = ((id * 137) % 361).toFloat()
    return Color.hsl(
        hue = hue,
        saturation = 0.4f,
        lightness = 0.92f
    )
}




@Preview
@Composable
fun PreviewNoteItem(){
    val noteEntity = NoteEntity(id = 0L, title = "Random Title", content = "Random Heading", priority = "Normal", reminderDate = "26 February, 2026")
    NotesItem(noteEntity, onClick = {}, onDelete = {})
}