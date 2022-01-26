package dev.katiebarnett.wordleextra.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dev.katiebarnett.wordleextra.*
import dev.katiebarnett.wordleextra.models.*
import dev.katiebarnett.wordleextra.ui.theme.WordleExtraTheme

@Composable
fun ShareBody(guesses: List<Guess>, shareGameAction: () -> Unit, newGameAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Guesses(guesses, modifier)

        Button(onClick = { shareGameAction.invoke() }) {
            Text(text = "Share Game")
        }

        Button(onClick = { newGameAction.invoke() }) {
            Text(text = "Play Again")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ShareScreenPreview() {
    val testGuesses = listOf(
        Guess(listOf(Incorrect('g'), Incorrect('r'), Misplaced('e'), Incorrect('a'), Incorrect('t'))),
        Guess(listOf(Incorrect('l'), Correct('i'), Misplaced('v'), Misplaced('e'), Incorrect('d'))),
        Guess(listOf(Correct('w'), Correct('i'), Correct('n'), Correct('c'), Correct('e')))
    )
    WordleExtraTheme {
        ShareBody(testGuesses, {}, {}, Modifier)
    }
}