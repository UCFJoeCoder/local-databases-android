package com.ucfjoe.localdatabasesandroid.domain.repository

import com.ucfjoe.localdatabasesandroid.domain.model.Team
import kotlinx.coroutines.flow.Flow

interface TeamRepository {

    suspend fun upsertTeam(team: Team)

    suspend fun deleteTeam(team: Team)

    fun getTeams(): Flow<List<Team>>

}