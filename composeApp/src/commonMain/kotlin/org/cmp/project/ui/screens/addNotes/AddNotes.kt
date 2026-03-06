package org.cmp.project.ui.screens.addNotes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.cmp.project.data.models.Priority
import org.cmp.project.data.sqldelight.NoteEntity
import org.cmp.project.domain.extensions.formatLongToDate
import org.cmp.project.resources.Res
import org.cmp.project.resources.add_description
import org.cmp.project.resources.add_notes
import org.cmp.project.resources.loading
import org.cmp.project.resources.update_notes
import org.cmp.project.ui.screens.home.HomeScreenContent
import org.cmp.project.ui.screens.viewModels.NoteViewModel
import org.cmp.project.ui.theme.LocalCustomColors
import org.cmp.project.ui.universalComponents.AppUniversalButton
import org.cmp.project.ui.universalComponents.BackButton
import org.jetbrains.compose.resources.stringResource

data class AddNotesScreen(
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
                AddNotes(noteEntity = note)
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AddNotes(noteEntity: NoteEntity?) {
    val priority = when(noteEntity?.priority){
        Priority.NORMAL.displayName -> Priority.NORMAL
        Priority.LOW.displayName -> Priority.LOW
        Priority.URGENT.displayName -> Priority.URGENT
        Priority.HIGH.displayName -> Priority.HIGH
        else -> Priority.NORMAL
    }

    var heading by remember { mutableStateOf(noteEntity?.title?:"") }
    var desc by remember { mutableStateOf(noteEntity?.content?:"") }
    var selectedPriority by remember { mutableStateOf(priority) }
    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDateText by remember { mutableStateOf(noteEntity?.reminderDate?:"No Reminder Set") }


    val navigator = LocalNavigator.currentOrThrow
    val colors = LocalCustomColors.current
    val viewModel : NoteViewModel = viewModel { NoteViewModel() }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
    ) {

        BackButton(
            modifier = Modifier.padding(start = 14.dp),
            screenName = stringResource(if(noteEntity == null)  Res.string.add_notes else Res.string.update_notes), onClick = {
            navigator.pop()
        })


        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.height(20.dp))


            BasicTextField(
                value = heading,
                maxLines = 2,
                onValueChange = { heading = it },
                textStyle = MaterialTheme.typography.displayLarge.copy(color = colors.blackColor),
                cursorBrush = SolidColor(colors.skyBlueColor),
                decorationBox = { innerTextField ->
                    if (heading.isEmpty()) Text("CMP TODO App Title", style = MaterialTheme.typography.headlineMedium, color = Color.Gray.copy(0.5f))
                    innerTextField()
                },
                modifier = Modifier.fillMaxWidth()
            )



            Text("Priority", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 20.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Priority.entries.forEach { priority ->
                    val isSelected = selectedPriority == priority
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedPriority = priority },
                        label = { Text(priority.displayName) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = priority.color.copy(alpha = 0.2f),
                            selectedLabelColor = priority.color
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            borderColor = if(isSelected) priority.color else Color.LightGray,
                            enabled = true,
                            selected = isSelected
                        )
                    )
                }
            }


            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(colors.skyBlueColor.copy(0.08f))
                    .clickable { showDatePicker = true }
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
                        text = selectedDateText,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                        color = colors.blackColor
                    )
                }

                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = Color.Gray.copy(0.6f)
                )
            }



            OutlinedTextField(
                value = desc,
                onValueChange = { desc = it },
                placeholder = { Text(stringResource(Res.string.add_description)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colors.skyBlueColor,
                    unfocusedBorderColor = colors.skyBlueColor.copy(0.3f)
                )
            )




            AppUniversalButton(
                onButtonClick = {
                    if(heading.isNotEmpty() && desc.isNotEmpty()){
                        if(noteEntity == null)
                        viewModel.insertInNotes(title = heading, content = desc, priority = selectedPriority.displayName, reminderDate = selectedDateText)
                        else
                            viewModel.updateNotes(title = heading, content = desc, priority = selectedPriority.displayName, reminderDate = selectedDateText, id = noteEntity.id)
                        navigator.push(HomeScreenContent())
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = colors.skyBlueColor),
                actionName = stringResource(if(noteEntity == null)  Res.string.add_notes else Res.string.update_notes),
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                contentPaddingValues = PaddingValues(vertical = 16.dp)
            )
        }



    }



    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    selectedDateText = datePickerState.selectedDateMillis.formatLongToDate()
                    showDatePicker = false
                }) {
                    Text("OK", color = colors.skyBlueColor)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}