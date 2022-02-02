@file:OptIn(ExperimentalMaterial3Api::class)

package dev.katiebarnett.wordleextra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.katiebarnett.wordleextra.models.Key
import dev.katiebarnett.wordleextra.screens.GameBody
import dev.katiebarnett.wordleextra.screens.ShareBody
import dev.katiebarnett.wordleextra.screens.StartGameBody
import dev.katiebarnett.wordleextra.ui.SnackbarScreen
import dev.katiebarnett.wordleextra.ui.theme.WordleExtraTheme
import kotlinx.coroutines.launch

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
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()
            viewModel.winGameAction = {
                navController.navigate(WordleExtraScreen.Share.name)
            }
            viewModel.invalidWordAction = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Word not in list"
                    )
                }
            }
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text(text = "Wordle Extra")}
                    )
                },
                snackbarHost = { snackbarHostState }
            ) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
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
                            viewModel.reset(this@MainActivity)
                            GameBody(
                                guesses = viewModel.guesses,
                                keyRow1 = viewModel.keyboardRow1,
                                keyRow2 = viewModel.keyboardRow2,
                                keyRow3 = viewModel.keyboardRow3,
                                keyAction = { key: Key -> viewModel.keyClickAction(key) },
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
                    SnackbarScreen(snackbarHostState, Modifier.align(Alignment.BottomCenter))
                }
            }
        }
    }
}