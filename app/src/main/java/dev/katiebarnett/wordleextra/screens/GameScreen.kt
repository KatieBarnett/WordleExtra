@file:OptIn(ExperimentalMaterial3Api::class)

package dev.katiebarnett.wordleextra.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dev.katiebarnett.wordleextra.*
import dev.katiebarnett.wordleextra.models.*
import dev.katiebarnett.wordleextra.ui.Heading
import dev.katiebarnett.wordleextra.ui.theme.WordleExtraTheme

@Composable
fun GameBody(
    guesses: List<Guess>,
    keyRow1: List<Key>,
    keyRow2: List<Key>,
    keyRow3: List<Key>,
    keyAction: (key: Key) -> Unit,
    newGameAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (heading, guessList, keyboard) = createRefs()
        Heading(
            Modifier
                .wrapContentSize()
                .constrainAs(heading) {
                    top.linkTo(parent.top)
                    bottom.linkTo(guessList.top)
                })
        IconButton(onClick = { newGameAction.invoke() }) {
            Icon(imageVector = Icons.TwoTone.Refresh, contentDescription = "New game")
        }
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

@Composable
fun Guesses(guesses: List<Guess>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
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
fun GameScreenPreview() {
    WordleExtraTheme {
        val testGuesses = listOf(
            Guess(listOf(Incorrect('g'), Incorrect('r'), Misplaced('e'), Incorrect('a'), Incorrect('t'))),
            Guess(listOf(Incorrect('l'), Correct('i'), Misplaced('v'), Misplaced('e'), Incorrect('d'))),
            Guess(listOf(Correct('w'), Correct('i'), Correct('n'), Correct('c'), Correct('e')))
        )
        GameBody(
            guesses = testGuesses,
            keyRow1 = initialKeyboardRow1,
            keyRow2 = initialKeyboardRow2,
            keyRow3 = initialKeyboardRow3,
            keyAction = {},
            newGameAction = {},
            modifier = Modifier
        )
    }
}