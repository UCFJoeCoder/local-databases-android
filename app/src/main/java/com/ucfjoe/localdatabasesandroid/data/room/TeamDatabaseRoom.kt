package com.ucfjoe.localdatabasesandroid.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucfjoe.localdatabasesandroid.data.room.dao.TeamDao
import com.ucfjoe.localdatabasesandroid.data.room.entity.TeamEntity

@Database(
    version = 1,
    entities = [TeamEntity::class],
    exportSchema = true
)
abstract class TeamDatabaseRoom : RoomDatabase() {
    abstract val teamDao: TeamDao
}