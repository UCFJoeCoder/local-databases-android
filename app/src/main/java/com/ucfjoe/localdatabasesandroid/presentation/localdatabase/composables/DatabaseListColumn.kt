package com.ucfjoe.localdatabasesandroid.presentation.localdatabase.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ucfjoe.localdatabasesandroid.domain.model.Team
import com.ucfjoe.localdatabasesandroid.presentation.localdatabase.DatabaseType
import com.ucfjoe.localdatabasesandroid.presentation.localdatabase.LocalDatabaseEvents

@Composable
fun DatabaseListColumn(
    modifier: Modifier = Modifier,
    databaseName: String,
    teams: List<Team>,
    databaseType: DatabaseType,
    onEvent: (LocalDatabaseEvents) -> Unit
) {
    Column(modifier) {
        Text(
            text = databaseName,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.onSurface
        )
        LazyColumn(Modifier.fillMaxSize()) {
            items(teams) { team ->
                TeamInfo(team = team, databaseType = databaseType, onEvent = onEvent)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDatabaseListColumn() {
    DatabaseListColumn(
        databaseName = "Room",
        teams = listOf(
            Team("Broncos", "Denver", "CO"),
            Team("Chiefs", "Kansas City", "MO"),
            Team("Raiders", "Las Vegas", "NV"),
            Team("Dolphins", "Miami", "FL"),
            Team("Jaguars", "Jacksonville", "FL")
        ),
        databaseType = DatabaseType.ROOM,
        onEvent = {})
}