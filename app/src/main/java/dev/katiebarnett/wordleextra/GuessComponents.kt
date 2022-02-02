package dev.katiebarnett.wordleextra

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import dev.katiebarnett.wordleextra.models.*
import dev.katiebarnett.wordleextra.ui.theme.Border
import dev.katiebarnett.wordleextra.ui.theme.WordleExtraTheme

@Composable
fun Guess(guess: Guess, modifier: Modifier = Modifier) {
    BoxWithConstraints {
        val letterWidth = (maxWidth / guess.letters.size).run {
            if (this > 50.dp) {
                50.dp
            } else {
                this
            }
        }
        val textSize = getFontSizeForBoxSize(letterWidth)
        Row(modifier = modifier) {
            guess.letters.forEach {
                GuessLetter(
                    it, fontSize = textSize, modifier = Modifier
                        .width(letterWidth)
                        .weight(1f)
                        .aspectRatio(1f)
                )
            }
        }
    }
}

@Composable
fun GuessLetter(letter: Letter, fontSize: TextUnit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        GuessLetterBackground(letter = letter)
        letter.char?.let {
            Text(text = it.uppercase(),
                fontSize = fontSize,
                color = letter.getForegroundColor(),
                style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun GuessLetterBackground(letter: Letter, modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 1.dp, color = Border,
                shape = RoundedCornerShape(10.dp)
            )
            .background(letter.getBackgroundColor())
    )
}

@Preview(showBackground = true)
@Composable
fun GuessPreview() {
    val testGuess = Guess(listOf(Incorrect('l'), Correct('i'), Misplaced('v'), Misplaced('e'), Incorrect('d')))
    WordleExtraTheme {
        Guess(testGuess, modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyGuessPreview() {
    val testGuess = Guess(listOf(Unknown(), Unknown(), Unknown(), Unknown(), Unknown()))
    WordleExtraTheme {
        Guess(testGuess, modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun LetterPreview() {
    WordleExtraTheme {
        Surface {
            Row {
                val blockModifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                GuessLetter(Incorrect('a'), getFontSizeForBoxSize(50.dp), blockModifier)
                GuessLetter(Misplaced('b'), getFontSizeForBoxSize(50.dp), blockModifier)
                GuessLetter(Correct('c'), getFontSizeForBoxSize(50.dp), blockModifier)
            }
        }
    }
}