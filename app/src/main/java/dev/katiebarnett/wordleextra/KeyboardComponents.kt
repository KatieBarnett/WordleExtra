package dev.katiebarnett.wordleextra

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.katiebarnett.wordleextra.models.*
import dev.katiebarnett.wordleextra.ui.theme.WordleExtraTheme
import kotlin.math.max


@Composable
fun Keyboard(keyRow1: List<Letter>, keyRow2: List<Letter>, keyRow3: List<Letter>, modifier: Modifier = Modifier) {
    BoxWithConstraints {
        val maxKeyCount = max(keyRow1.size, max(keyRow2.size, keyRow3.size))
        val keyWidth = (maxWidth / maxKeyCount)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            KeyboardRow(keyRow = keyRow1, keyWidth, modifier)
            KeyboardRow(keyRow = keyRow2, keyWidth ,modifier)
            KeyboardRow(keyRow = keyRow3, keyWidth, modifier)
        }
    }
}

@Composable
fun KeyboardRow(keyRow: List<Letter>, keyWidth: Dp, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        keyRow.forEach {
            Key(it, modifier.width(keyWidth))
        }
    }
}

@Composable
fun Key(letter: Letter, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .padding(2.dp)
        .aspectRatio(1.5f),
        contentAlignment = Alignment.Center
    ) {
        KeyBackground(letter = letter)
        letter.char?.let {
            Text(text = it.uppercase())
        }
    }
}

@Composable
fun KeyBackground(letter: Letter, modifier: Modifier = Modifier){
    val backgroundColor = when(letter) {
        is Incorrect -> Color.Yellow
        is Correct -> Color.Green
        is Misplaced -> Color.Gray
        is Unknown -> Color.White
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
            .background(backgroundColor)
    )
}

@Preview(showBackground = true)
@Composable
fun KeyboardPreview() {
    WordleExtraTheme {
        val modifier = Modifier
        Keyboard(keyRow1 = initialKeyboardRow1, keyRow2 = initialKeyboardRow2, keyRow3 = initialKeyboardRow3, modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun KeyPreview() {
    WordleExtraTheme {
        Surface {
            Row {
                val modifier = Modifier.width(50.dp)
                Key(Incorrect('a'), modifier)
                Key(Misplaced('b'), modifier)
                Key(Correct('c'), modifier)
            }
        }
    }
}