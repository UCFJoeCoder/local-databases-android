package com.ucfjoe.localdatabasesandroid.data.room.repository

import com.ucfjoe.localdatabasesandroid.data.room.dao.TeamDao
import com.ucfjoe.localdatabasesandroid.data.room.entity.toTeam
import com.ucfjoe.localdatabasesandroid.data.room.entity.toTeamEntity
import com.ucfjoe.localdatabasesandroid.domain.model.Team
import com.ucfjoe.localdatabasesandroid.domain.repository.TeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TeamRepositoryImpl(
  private val teamDao: TeamDao
) : TeamRepository {

    override suspend fun upsertTeam(team: Team) {
        teamDao.upsertTeam(team.toTeamEntity())
    }

    override suspend fun deleteTeam(team: Team) {
        teamDao.deleteTeam(team.toTeamEntity())
    }

    override fun getTeams(): Flow<List<Team>> {
        return teamDao.getTeams().map { list -> list.map { it.toTeam() } }
    }
}