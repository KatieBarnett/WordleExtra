package dev.katiebarnett.wordleextra

import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material.icons.outlined.KeyboardReturn
import androidx.compose.material3.*
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
fun Keyboard(keyRow1: List<Key>, keyRow2: List<Key>, keyRow3: List<Key>, keyAction: (key: Key) -> Unit, modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier) {
        val maxKeyCount = max(keyRow1.size, max(keyRow2.size, keyRow3.size))
        val keyWidth = (maxWidth / maxKeyCount)
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
            KeyboardRow(keyRow = keyRow1, keyAction, keyWidth, modifier)
            KeyboardRow(keyRow = keyRow2, keyAction, keyWidth ,modifier)
            KeyboardRow(keyRow = keyRow3, keyAction, keyWidth, modifier)
        }
    }
}

@Composable
fun KeyboardRow(keyRow: List<Key>, keyAction: (key: Key) -> Unit, keyWidth: Dp, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        keyRow.forEach {
            Key(it, keyAction, modifier.width(keyWidth))
        }
    }
}

@Composable
fun Key(key: Key, action: (key: Key) -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .padding(2.dp)
        .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        KeyBackground(key = key)
        if (key is Letter) {
            key.char?.let {
                TextButton(
                    onClick = { action.invoke(key) },
                    content = {
                        Text(text = it.uppercase())
                    })
            }
        } else if (key is Backspace) {
            IconButton(
                onClick = { action.invoke(key) },
                content = {
                    Icon(imageVector = Icons.Outlined.Backspace,
                        contentDescription = "Backspace",
                        tint = Color.Black)
                }
            )
        } else if (key is Enter) {
            IconButton(
                onClick = { action.invoke(key) },
                content = {
                    Icon(imageVector = Icons.Outlined.KeyboardReturn,
                        contentDescription = "Enter",
                        tint = Color.Black)
                }
            )
        }
    }
}

@Composable
fun KeyBackground(key: Key, modifier: Modifier = Modifier){
    val backgroundColor = when(key) {
        is Incorrect -> Color.Gray
        is Correct -> Color.Green
        is Misplaced -> Color.Yellow
        is Unknown -> Color.White
        Backspace -> Color.White
        Enter -> Color.White
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
        Keyboard(keyRow1 = initialKeyboardRow1, keyRow2 = initialKeyboardRow2, keyRow3 = initialKeyboardRow3, {}, modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun KeyPreview() {
    WordleExtraTheme {
        Surface {
            Row {
                val modifier = Modifier.width(50.dp)
                Key(Incorrect('a'), {}, modifier)
                Key(Misplaced('b'), {}, modifier)
                Key(Correct('c'), {}, modifier)
                Key(Unknown('d'), {}, modifier)
                Key(Backspace, {}, modifier)
                Key(Enter, {}, modifier)
            }
        }
    }
}