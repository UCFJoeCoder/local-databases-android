package com.ucfjoe.localdatabasesandroid.presentation.localdatabase.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ucfjoe.localdatabasesandroid.presentation.localdatabase.LocalDatabaseEvents
import com.ucfjoe.localdatabasesandroid.presentation.localdatabase.TeamConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTeamPanel(
    selectedTeamName: String,
    onEvent: (LocalDatabaseEvents) -> Unit
) {
    var isDropDownExpanded by remember { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ExposedDropdownMenuBox(
            expanded = isDropDownExpanded,
            onExpandedChange = { isDropDownExpanded = it }
        ) {
            OutlinedTextField(
                value = selectedTeamName,
                onValueChange = { },
                readOnly = true,
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor(),
                label = { Text(text = "Select Team") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropDownExpanded)
                }
            )
            ExposedDropdownMenu(
                expanded = isDropDownExpanded,
                onDismissRequest = { isDropDownExpanded = false }
            ) {
                TeamConstants.listOfTeams.forEach { team ->
                    DropdownMenuItem(
                        text = { Text(text = team.name) },
                        onClick = {
                            onEvent(LocalDatabaseEvents.OnSelectedTeamChanged(team.name))
                            isDropDownExpanded = false
                        }
                    )
                }
            }
        }
        Button(
            onClick = { onEvent(LocalDatabaseEvents.OnCreateTeam) },
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Add Team"
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewCreateTeamPanel() {
    CreateTeamPanel(
        selectedTeamName = "Jaguars",
        onEvent = {}
    )
}