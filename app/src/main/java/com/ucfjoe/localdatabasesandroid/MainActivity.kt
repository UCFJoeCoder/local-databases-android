package com.ucfjoe.localdatabasesandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ucfjoe.localdatabasesandroid.presentation.localdatabase.LocalDatabaseScreen
import com.ucfjoe.localdatabasesandroid.presentation.theme.TeamPlayersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamPlayersTheme(){
                LocalDatabaseScreen()
            }
        }
    }
}
