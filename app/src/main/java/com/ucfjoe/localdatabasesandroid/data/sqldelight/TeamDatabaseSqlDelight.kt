package com.ucfjoe.localdatabasesandroid.data.sqldelight

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.ucfjoe.localdatabasesandroid.TeamDatabase
import com.ucfjoe.localdatabasesandroid.data.sqldelight.repository.TeamRepositoryImpl
import com.ucfjoe.localdatabasesandroid.domain.repository.TeamRepository

class TeamDatabaseSqlDelight {

    companion object {
        fun getTeamRepository(context: Context): TeamRepository {
            val driver = AndroidSqliteDriver(
                schema = TeamDatabase.Schema,
                context = context,
                name = "team_delight.db"
            )

            return TeamRepositoryImpl(TeamDatabase(driver))
        }
    }
}