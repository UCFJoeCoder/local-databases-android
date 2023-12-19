package com.ucfjoe.localdatabasesandroid.presentation.localdatabase

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucfjoe.localdatabasesandroid.di.ImplRoom
import com.ucfjoe.localdatabasesandroid.di.ImplSqlDelight
import com.ucfjoe.localdatabasesandroid.domain.repository.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalDatabaseViewModel @Inject constructor(
    @ImplRoom private val teamRepositoryRoom: TeamRepository,
    @ImplSqlDelight private val teamRepositorySqlDelight: TeamRepository
) : ViewModel() {

    private val _state = mutableStateOf(LocalDatabaseState())
    val state: State<LocalDatabaseState> = _state

    init {
        viewModelScope.launch {
            teamRepositoryRoom.getTeams().onEach {
                _state.value = state.value.copy(teamsRoom = it)
            }.launchIn(viewModelScope)
        }
        viewModelScope.launch {
            teamRepositorySqlDelight.getTeams().onEach {
                _state.value = state.value.copy(teamsSqlDelight = it)
            }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event: LocalDatabaseEvents) {
        when (event) {
            is LocalDatabaseEvents.OnActiveDatabaseChanged -> {
                _state.value = state.value.copy(activeDatabaseType = event.activeDatabase)
            }

            LocalDatabaseEvents.OnCreateTeam -> {
                val newTeam = state.value.selectedTeam
                if (newTeam.name.isBlank()) {
                    return
                }

                val activeDatabase = state.value.activeDatabaseType
                viewModelScope.launch {
                    if (activeDatabase == ActiveDatabaseType.ROOM || activeDatabase == ActiveDatabaseType.BOTH) {
                        teamRepositoryRoom.upsertTeam(newTeam)
                    }
                    if (activeDatabase == ActiveDatabaseType.SQL_DELIGHT || activeDatabase == ActiveDatabaseType.BOTH) {
                        teamRepositorySqlDelight.upsertTeam(newTeam)
                    }
                }
            }

            is LocalDatabaseEvents.OnDeleteTeam -> {
                viewModelScope.launch {
                    if (event.databaseType == DatabaseType.ROOM) {
                        teamRepositoryRoom.deleteTeam(event.team)
                    } else {
                        teamRepositorySqlDelight.deleteTeam(event.team)
                    }
                }
            }

            is LocalDatabaseEvents.OnSelectedTeamChanged -> {
                _state.value = state.value.copy(selectedTeam = TeamConstants.getTeamByName(event.teamName))
            }
        }
    }

}