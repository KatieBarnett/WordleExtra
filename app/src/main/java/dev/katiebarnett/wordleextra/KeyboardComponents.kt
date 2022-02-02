package dev.katiebarnett.wordleextra

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material.icons.outlined.KeyboardReturn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import dev.katiebarnett.wordleextra.models.*
import dev.katiebarnett.wordleextra.ui.theme.Border
import dev.katiebarnett.wordleextra.ui.theme.WordleExtraTheme
import kotlin.math.max


@Composable
fun Keyboard(keyRow1: List<Key>, keyRow2: List<Key>, keyRow3: List<Key>, keyAction: (key: Key) -> Unit, modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier) {
        val maxKeyCount = max(keyRow1.size, max(keyRow2.size, keyRow3.size))
        val keyWidth = (maxWidth / maxKeyCount)
        val textSize = getFontSizeForBoxSize(keyWidth)
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
            KeyboardRow(keyRow = keyRow1, keyAction, keyWidth, textSize, modifier)
            KeyboardRow(keyRow = keyRow2, keyAction, keyWidth, textSize, modifier)
            KeyboardRow(keyRow = keyRow3, keyAction, keyWidth, textSize, modifier)
        }
    }
}

@Composable
fun KeyboardRow(keyRow: List<Key>, keyAction: (key: Key) -> Unit, keyWidth: Dp, fontSize: TextUnit, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        keyRow.forEach {
            Key(it, keyAction, fontSize, modifier.width(keyWidth))
        }
    }
}

@Composable
fun Key(key: Key, action: (key: Key) -> Unit, fontSize: TextUnit, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .padding(2.dp)
        .aspectRatio(0.75f),
        contentAlignment = Alignment.Center
    ) {
        KeyBackground(key = key)
        if (key is Letter) {
            key.char?.let {
                TextButton(
                    contentPadding = PaddingValues(0.dp),
                    onClick = { action.invoke(key) },
                    content = {
                        Text(
                            color = key.getForegroundColor(),
                            text = it.uppercase(),
                            fontSize = fontSize,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    })
            }
        } else if (key is Backspace) {
            IconButton(
                onClick = { action.invoke(key) },
                content = {
                    Icon(imageVector = Icons.Outlined.Backspace,
                        contentDescription = "Backspace",
                        tint = key.getForegroundColor())
                }
            )
        } else if (key is Enter) {
            IconButton(
                onClick = { action.invoke(key) },
                content = {
                    Icon(imageVector = Icons.Outlined.KeyboardReturn,
                        contentDescription = "Enter",
                        tint = key.getForegroundColor())
                }
            )
        }
    }
}

@Composable
fun KeyBackground(key: Key, modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = Border, shape = RoundedCornerShape(10.dp))
            .background(key.getBackgroundColor())
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
                Key(Incorrect('a'), {}, getFontSizeForBoxSize(50.dp), modifier)
                Key(Misplaced('b'), {}, getFontSizeForBoxSize(50.dp), modifier)
                Key(Correct('c'), {}, getFontSizeForBoxSize(50.dp), modifier)
                Key(Unknown('d'), {}, getFontSizeForBoxSize(50.dp), modifier)
                Key(Backspace, {}, getFontSizeForBoxSize(50.dp), modifier)
                Key(Enter, {}, getFontSizeForBoxSize(50.dp), modifier)
            }
        }
    }
}