package com.ucfjoe.localdatabasesandroid.data.sqldelight.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ucfjoe.localdatabasesandroid.TeamDatabase
import com.ucfjoe.localdatabasesandroid.domain.model.Team
import com.ucfjoe.localdatabasesandroid.domain.repository.TeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamRepositoryImpl(
    db: TeamDatabase
) : TeamRepository {

    private val queries = db.teamsQueries

    override suspend fun upsertTeam(team: Team) {
        val id = if (team.id == 0L) null else team.id
        queries.upsertTeam(id, team.name, team.city, team.state)
    }

    override suspend fun deleteTeam(team: Team) {
        queries.deleteTeamById(team.id)
    }

    override fun getTeams(): Flow<List<Team>> {
        return queries.getAllTeams().asFlow().mapToList(Dispatchers.IO)
            .map { list -> list.map { it.toTeam() } }
    }
}