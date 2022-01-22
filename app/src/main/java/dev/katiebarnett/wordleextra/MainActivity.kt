@file:OptIn(ExperimentalMaterial3Api::class)

package dev.katiebarnett.wordleextra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import dev.katiebarnett.wordleextra.models.Correct
import dev.katiebarnett.wordleextra.models.Guess
import dev.katiebarnett.wordleextra.models.Incorrect
import dev.katiebarnett.wordleextra.models.Misplaced
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
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Heading()
                        GameContent(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun GameContent(
    viewModel: MainViewModel
) {
    Guesses(viewModel.guesses)
}

@Composable
fun Guesses(guesses: List<Guess>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(guesses) { guess ->
            Guess(guess, modifier)
        }
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
            Column(modifier = Modifier.padding(innerPadding)) {
                Heading()
                GuessesPreview()
            }
        }
    }
}