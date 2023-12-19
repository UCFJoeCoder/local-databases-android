package com.ucfjoe.localdatabasesandroid.presentation.localdatabase.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ucfjoe.localdatabasesandroid.domain.model.Team
import com.ucfjoe.localdatabasesandroid.presentation.localdatabase.DatabaseType
import com.ucfjoe.localdatabasesandroid.presentation.localdatabase.LocalDatabaseEvents

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeamInfo(
    team: Team,
    databaseType: DatabaseType,
    onEvent: (LocalDatabaseEvents) -> Unit
) {
    Card(Modifier
        .fillMaxWidth()
        .padding(6.dp)
        .combinedClickable(
            onClick = {},
            onLongClick = { onEvent(LocalDatabaseEvents.OnDeleteTeam(team, databaseType)) }
        )
    ) {
        Column(Modifier.padding(6.dp)) {
            Text(text = team.name)
            Text(text = "${team.city}, ${team.state}")
        }
    }
}

@Preview
@Composable
fun PreviewTeamInfo() {
    TeamInfo(
        Team("Jaguars", "Jacksonville", "FL"),
        DatabaseType.ROOM,
        {}
    )
}





