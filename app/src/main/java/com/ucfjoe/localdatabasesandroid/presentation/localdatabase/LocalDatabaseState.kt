package com.ucfjoe.localdatabasesandroid.presentation.localdatabase

import com.ucfjoe.localdatabasesandroid.domain.model.Team

data class LocalDatabaseState(
    val teamsRoom: List<Team> = emptyList(),
    val teamsSqlDelight: List<Team> = emptyList(),
    val activeDatabaseType: ActiveDatabaseType = ActiveDatabaseType.ROOM,
    val errorMessage: String = "",
    val selectedTeam: Team = Team("", "", "", 0L)
)

enum class ActiveDatabaseType {
    ROOM, SQL_DELIGHT, BOTH
}

enum class DatabaseType {
    ROOM, SQL_DELIGHT
}
