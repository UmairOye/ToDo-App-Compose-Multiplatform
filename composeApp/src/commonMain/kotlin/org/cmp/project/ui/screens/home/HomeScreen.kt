package org.cmp.project.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.cmp.project.resources.Res
import org.cmp.project.resources.content_description_search
import org.cmp.project.resources.hello
import org.cmp.project.resources.search_note
import org.cmp.project.ui.screens.addNotes.AddNotesScreen
import org.cmp.project.ui.screens.noteDetails.NoteDetailsScreenContent
import org.cmp.project.ui.screens.viewModels.NoteViewModel
import org.cmp.project.ui.theme.LocalCustomColors
import org.cmp.project.ui.universalComponents.NotesItem
import org.cmp.project.ui.universalComponents.NothingFound
import org.jetbrains.compose.resources.stringResource


class HomeScreenContent: Screen{
    @Composable
    override fun Content() {
        HomeScreen()
    }

}
@Composable
fun HomeScreen() {
    val navigator = LocalNavigator.currentOrThrow
    val colors = LocalCustomColors.current
    val viewModel : NoteViewModel = viewModel { NoteViewModel() }
    val allNotes by viewModel.filteredNotes.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    navigator.push(AddNotesScreen(-1L))
                },
                containerColor = colors.skyBlueColor,
                contentColor = colors.whiteColor
            ) {
                Icon(Icons.Filled.Add, "Small floating action button.")
            }

        },


        content = {
            Column(
                modifier = Modifier.fillMaxSize()
                    .background(color = colors.whiteColor).padding(horizontal = 16.dp)
                    .navigationBarsPadding()
                    .statusBarsPadding(),
            ) {

                    Text(
                        text = stringResource(Res.string.hello),
                        style = MaterialTheme.typography.displayLarge,
                        color = colors.blackColor
                    )


                TextField(
                    value = searchQuery,
                    onValueChange = { viewModel.updateSearchQuery(it) },
                    placeholder = {
                        Text(
                            text = stringResource(Res.string.search_note),
                            style = MaterialTheme.typography.bodyLarge
                        ) },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedTextColor = colors.edTextColor,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            tint = colors.skyBlueColor,
                            contentDescription = stringResource(Res.string.content_description_search))
                    },

                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp).
                    border(width = 1.dp, color = colors.skyBlueColor, shape = RoundedCornerShape(16.dp))
                )



                if(allNotes.isNotEmpty()){
                    LazyVerticalStaggeredGrid(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalItemSpacing = 12.dp,
                        contentPadding = PaddingValues(bottom = 8.dp),
                        columns = StaggeredGridCells.Fixed(2),
                        modifier = Modifier.padding(top = 12.dp)
                    ) {

                        items(allNotes, key = {it.id }) { model ->
                            NotesItem(model,
                                onClick = { navigator.push(NoteDetailsScreenContent(noteId = model.id)) },
                                onDelete = {
                                    viewModel.deleteNote(model.id)
                                })
                        }
                    }
                }else{
                    NothingFound(modifier = Modifier.weight(1f))
                }
            }
        }
    )


}


@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}