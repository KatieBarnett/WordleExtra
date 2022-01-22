package dev.katiebarnett.wordleextra

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.katiebarnett.wordleextra.models.*
import dev.katiebarnett.wordleextra.ui.theme.WordleExtraTheme

@Composable
fun Guess(guess: Guess) {
    Row {
        guess.letters.forEach {
            Letter(it)
        }
    }
}

@Composable
fun Letter(letter: Letter) {
    Text(text = letter.char.uppercase())
}

@Composable
fun Heading() {
    Column {
        Text(text = "A Jetpack Compose Experiment", style = MaterialTheme.typography.bodySmall)
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
        Guesses(testGuesses)
    }
}

@Preview(showBackground = true)
@Composable
fun GuessPreview() {
    val testGuess = Guess(listOf(Incorrect('l'), Correct('i'), Misplaced('v'), Misplaced('e'), Incorrect('d')))
    WordleExtraTheme {
        Guess(testGuess)
    }
}

@Preview(showBackground = true)
@Composable
fun LetterPreview() {
    WordleExtraTheme {
        Row {
            Letter(Incorrect('a'))
            Letter(Misplaced('b'))
            Letter(Correct('c'))
        }
    }
}