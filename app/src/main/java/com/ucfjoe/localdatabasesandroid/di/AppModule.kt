package com.ucfjoe.localdatabasesandroid.di

import android.content.Context
import androidx.room.Room
import com.ucfjoe.localdatabasesandroid.data.room.TeamDatabaseRoom
import com.ucfjoe.localdatabasesandroid.data.room.repository.TeamRepositoryImpl
import com.ucfjoe.localdatabasesandroid.data.sqldelight.TeamDatabaseSqlDelight
import com.ucfjoe.localdatabasesandroid.domain.repository.TeamRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesTeamDatabase(@ApplicationContext appContext: Context): TeamDatabaseRoom {
        return Room.databaseBuilder(
            appContext,
            TeamDatabaseRoom::class.java,
            "team_room.db"
        ).build()
    }

    @ImplRoom
    @Provides
    @Singleton
    fun providesTeamRepositoryRoom(db: TeamDatabaseRoom): TeamRepository {
        return TeamRepositoryImpl(db.teamDao)
    }

    @ImplSqlDelight
    @Provides
    @Singleton
    fun providesTeamRepositorySqlDelight(@ApplicationContext appContext: Context): TeamRepository {
        return TeamDatabaseSqlDelight.getTeamRepository(appContext)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ImplRoom

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ImplSqlDelight
