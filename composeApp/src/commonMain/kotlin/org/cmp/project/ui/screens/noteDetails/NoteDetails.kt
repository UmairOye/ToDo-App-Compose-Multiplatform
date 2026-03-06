package org.cmp.project.ui.screens.noteDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.cmp.project.data.models.Priority
import org.cmp.project.data.sqldelight.NoteEntity
import org.cmp.project.resources.Res
import org.cmp.project.resources.loading
import org.cmp.project.ui.screens.addNotes.AddNotesScreen
import org.cmp.project.ui.screens.viewModels.NoteViewModel
import org.cmp.project.ui.theme.LocalCustomColors
import org.cmp.project.ui.universalComponents.BackButton
import org.jetbrains.compose.resources.stringResource


data class NoteDetailsScreenContent(
    val noteId: Long
): Screen{
    @Composable
    override fun Content() {

        val viewModel: NoteViewModel = viewModel { NoteViewModel() }
        val note by viewModel.getNoteById(noteId).collectAsStateWithLifecycle(initialValue = null)
        when {
            noteId > 0L && note == null -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = stringResource(Res.string.loading),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }
            }

            else -> {
                NoteDetails(noteEntity = note)
            }
        }
    }

}

@Composable
fun NoteDetails(noteEntity: NoteEntity?) {

    val colors = LocalCustomColors.current
    val navigator = LocalNavigator.currentOrThrow
    val priorityColor = when(noteEntity?.priority){
        Priority.LOW.displayName -> Priority.LOW.color
        Priority.HIGH.displayName -> Priority.HIGH.color
        Priority.NORMAL.displayName -> Priority.NORMAL.color
        Priority.URGENT.displayName -> Priority.URGENT.color
        else -> Priority.LOW.color
    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    navigator.push(AddNotesScreen(noteEntity?.id ?: -1))
                },
                containerColor = colors.skyBlueColor,
                contentColor = colors.whiteColor
            ) {
                Icon(Icons.Filled.Edit, "Small floating action button.")
            }

        },


        content = {
            Column(
                modifier = Modifier.fillMaxSize()
                    .background(color = colors.whiteColor)
                    .navigationBarsPadding()
                    .statusBarsPadding()
            ) {


                BackButton(modifier = Modifier.padding(start = 14.dp), screenName = "Note Details", onClick = {
                    navigator.pop()
                })


                Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(horizontal = 16.dp)) {
                    Spacer(modifier = Modifier.height(20.dp))


                    Text(
                        text = noteEntity?.title?:"",
                        style = MaterialTheme.typography.displayLarge.copy(color = colors.blackColor),
                        modifier = Modifier.fillMaxWidth()
                    )



                    Text("Priority", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 20.dp))


                    FilterChip(
                        selected = true,
                        onClick = { },
                        label = { Text(noteEntity?.priority?:"") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = priorityColor.copy(alpha = 0.2f),
                            selectedLabelColor = priorityColor
                        ),

                        border = FilterChipDefaults.filterChipBorder(
                            borderColor = priorityColor ,
                            enabled = true,
                            selected = true
                        )
                    )



                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(colors.skyBlueColor.copy(0.08f))
                            .clickable { }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            tint = colors.skyBlueColor
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Reminder Date",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Gray
                            )
                            Text(
                                text = noteEntity?.reminderDate?:"",
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                                color = colors.blackColor
                            )
                        }
                    }


                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    Text(
                        text = noteEntity?.content?:"",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }




            }


        })


}