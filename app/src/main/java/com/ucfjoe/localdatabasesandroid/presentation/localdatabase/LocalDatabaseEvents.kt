package com.ucfjoe.localdatabasesandroid.presentation.localdatabase

import com.ucfjoe.localdatabasesandroid.domain.model.Team

sealed class LocalDatabaseEvents {

    data class OnActiveDatabaseChanged(val activeDatabase: ActiveDatabaseType) :
        LocalDatabaseEvents()

    data class OnSelectedTeamChanged(val teamName: String) : LocalDatabaseEvents()

    data object OnCreateTeam : LocalDatabaseEvents()

    data class OnDeleteTeam(val team: Team, val databaseType: DatabaseType) : LocalDatabaseEvents()

}
