package com.ucfjoe.localdatabasesandroid.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ucfjoe.localdatabasesandroid.domain.model.Team

@Entity(tableName = "teams")
data class TeamEntity(
    val name: String,

    val city: String,

    val state: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)

fun TeamEntity.toTeam(): Team {
    return Team(name, city, state, id)
}

fun Team.toTeamEntity(): TeamEntity {
    return TeamEntity(name, city, state, id)
}
