package dev.katiebarnett.wordleextra.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.katiebarnett.wordleextra.ui.theme.Foreground
import dev.katiebarnett.wordleextra.ui.theme.Surface
import dev.katiebarnett.wordleextra.ui.theme.WordleExtraTheme

@Composable
fun ShareBody(shareGameAction: () -> Unit, newGameAction: () -> Unit, modifier: Modifier = Modifier) {
        Box(modifier = modifier.background(Foreground)) {
        Column(
            modifier
                .padding(16.dp)
        ) {

            Text(text = "Nice work, you guessed the word!")

            Button(onClick = { shareGameAction.invoke() }) {
                Text(text = "Share Game")
            }

            Button(onClick = { newGameAction.invoke() }) {
                Text(text = "Play Again")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShareScreenPreview() {
    WordleExtraTheme {
        ShareBody({}, {}, Modifier)
    }
}