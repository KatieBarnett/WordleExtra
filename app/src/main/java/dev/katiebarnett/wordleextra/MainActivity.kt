@file:OptIn(ExperimentalMaterial3Api::class)

package dev.katiebarnett.wordleextra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dev.katiebarnett.wordleextra.models.*
import dev.katiebarnett.wordleextra.ui.theme.WordleExtraTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordleExtraTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Wordle Extra")}
                        )
                    }
                ) { innerPadding ->
                    Layout(
                        guesses = viewModel.guesses,
                        keyRow1 = viewModel.keyboardRow1,
                        keyRow2 = viewModel.keyboardRow2,
                        keyRow3 = viewModel.keyboardRow3,
                        keyAction = { key: Key -> viewModel.keyClickAction(key) },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Guesses(guesses: List<Guess>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(guesses) { guess ->
            Guess(guess, modifier)
        }
    }
}

@Composable
fun Layout(guesses: List<Guess>, keyRow1: List<Key>, keyRow2: List<Key>, keyRow3: List<Key>, keyAction: (key: Key) -> Unit, modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (heading, guessList, keyboard) = createRefs()
        Heading(Modifier
            .wrapContentSize()
            .constrainAs(heading) {
                top.linkTo(parent.top)
                bottom.linkTo(guessList.top)
            })
        Guesses(guesses,
            Modifier
                .constrainAs(guessList) {
                top.linkTo(heading.bottom, margin = 16.dp)
                bottom.linkTo(keyboard.top, margin = 16.dp)
                height = Dimension.fillToConstraints
            })
        Keyboard(keyRow1, keyRow2, keyRow3, keyAction,
            Modifier
                .wrapContentSize()
                .constrainAs(keyboard) {
            top.linkTo(guessList.bottom)
            bottom.linkTo(parent.bottom)
        })
    }
}

@Preview(showBackground = true)
@Composable
fun GuessesPreview() {
    val testGuesses = listOf(
        Guess(listOf(Incorrect('g'), Incorrect('r'), Misplaced('e'), Incorrect('a'), Incorrect('t'))),
        Guess(listOf(Incorrect('l'), Correct('i'), Misplaced('v'), Misplaced('e'), Incorrect('d'))),
        Guess(listOf(Correct('w'), Correct('i'), Correct('n'), Correct('c'), Correct('e')))
    )
    WordleExtraTheme {
        Guesses(testGuesses, modifier = Modifier
            .width(200.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WordleExtraTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Wordle Extra")}
                )
            }) { innerPadding ->
            val testGuesses = listOf(
                Guess(listOf(Incorrect('g'), Incorrect('r'), Misplaced('e'), Incorrect('a'), Incorrect('t'))),
                Guess(listOf(Incorrect('l'), Correct('i'), Misplaced('v'), Misplaced('e'), Incorrect('d'))),
                Guess(listOf(Correct('w'), Correct('i'), Correct('n'), Correct('c'), Correct('e')))
            )
            Layout(
                guesses = testGuesses,
                keyRow1 = initialKeyboardRow1,
                keyRow2 = initialKeyboardRow2,
                keyRow3 = initialKeyboardRow3,
                keyAction = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}