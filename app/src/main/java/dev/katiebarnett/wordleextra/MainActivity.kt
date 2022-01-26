@file:OptIn(ExperimentalMaterial3Api::class)

package dev.katiebarnett.wordleextra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.katiebarnett.wordleextra.models.*
import dev.katiebarnett.wordleextra.screens.StartGameBody
import dev.katiebarnett.wordleextra.screens.GameBody
import dev.katiebarnett.wordleextra.screens.ShareBody
import dev.katiebarnett.wordleextra.ui.theme.WordleExtraTheme

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.reset(this)
        setContent {
            WordleExtraApp()
        }
    }

    @Composable
    fun WordleExtraApp() {
        WordleExtraTheme {
            val navController = rememberNavController()
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text(text = "Wordle Extra")}
                    )
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = WordleExtraScreen.StartGame.name,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    viewModel.winGameAction = {
                        navController.navigate(WordleExtraScreen.StartGame.name)
                    }

                    composable(WordleExtraScreen.StartGame.name) {
                        StartGameBody(startGameAction = {
                            navController.navigate(WordleExtraScreen.Game.name)
                        })
                    }
                    composable(WordleExtraScreen.Game.name) {
                        GameBody(
                            guesses = viewModel.guesses,
                            keyRow1 = viewModel.keyboardRow1,
                            keyRow2 = viewModel.keyboardRow2,
                            keyRow3 = viewModel.keyboardRow3,
                            keyAction = { key: Key -> viewModel.keyClickAction(key)},
                            newGameAction = {
                                navController.navigate(WordleExtraScreen.StartGame.name)
                            }
                        )
                    }
                    composable(WordleExtraScreen.Share.name) {
                        ShareBody(
                            guesses = viewModel.guesses,
                            shareGameAction = {
                                // TODO
                            },
                            newGameAction = {
                                navController.navigate(WordleExtraScreen.StartGame.name)
                            })
                    }
                }
            }
        }
    }
}