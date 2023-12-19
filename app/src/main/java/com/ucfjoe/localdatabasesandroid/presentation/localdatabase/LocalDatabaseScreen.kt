package com.ucfjoe.localdatabasesandroid.presentation.localdatabase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucfjoe.localdatabasesandroid.domain.model.Team
import com.ucfjoe.localdatabasesandroid.presentation.localdatabase.composables.CreateTeamPanel
import com.ucfjoe.localdatabasesandroid.presentation.localdatabase.composables.DatabaseListColumn
import com.ucfjoe.localdatabasesandroid.presentation.localdatabase.composables.DatabaseSelectionRadioButtons

@Composable
fun LocalDatabaseScreen(
    viewModel: LocalDatabaseViewModel = hiltViewModel()
) {
    LocalDatabaseScreen(
        state = viewModel.state.value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun LocalDatabaseScreen(
    state: LocalDatabaseState,
    onEvent: (LocalDatabaseEvents) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CreateTeamPanel(selectedTeamName = state.selectedTeam.name, onEvent = onEvent)

        DatabaseSelectionRadioButtons(state = state, onEvent = onEvent)

        Row(Modifier.fillMaxWidth()) {
            DatabaseListColumn(
                modifier = Modifier.fillMaxWidth(.5f),
                databaseName = "Room",
                teams = state.teamsRoom,
                databaseType = DatabaseType.ROOM,
                onEvent = onEvent
            )
            DatabaseListColumn(
                modifier = Modifier.fillMaxWidth(),
                databaseName = "SQLDelight",
                teams = state.teamsSqlDelight,
                databaseType = DatabaseType.SQL_DELIGHT,
                onEvent = onEvent
            )
        }
    }
}

@Preview
@Composable
fun PreviewLocalDatabaseScreen() {
    LocalDatabaseScreen(
        state = LocalDatabaseState(
            teamsRoom = listOf(
                Team("Broncos", "Denver", "CO"),
                Team("Chiefs", "Kansas City", "MO"),
                Team("Raiders", "Las Vegas", "NV")
            ),
            teamsSqlDelight = listOf(
                Team("Dolphins", "Miami", "FL"),
                Team("Jaguars", "Jacksonville", "FL")
            )
        ),
        onEvent = {}
    )
}