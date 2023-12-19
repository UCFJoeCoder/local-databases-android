package com.ucfjoe.localdatabasesandroid.data.sqldelight.repository

import com.ucfjoe.localdatabasesandroid.Teams
import com.ucfjoe.localdatabasesandroid.domain.model.Team

fun Teams.toTeam() : Team {
    return Team(name, city, state, id)
}

fun Team.toTeams() : Teams {
    return Teams(id, name, city, state)
}