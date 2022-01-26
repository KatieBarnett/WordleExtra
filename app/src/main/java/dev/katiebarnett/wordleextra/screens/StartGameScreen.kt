@file:OptIn(ExperimentalMaterial3Api::class)

package dev.katiebarnett.wordleextra.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.katiebarnett.wordleextra.ui.theme.WordleExtraTheme

@Composable
fun StartGameBody(startGameAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(onClick = { startGameAction.invoke() }) {
            Text(text = "Start Game")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun StartGameScreenPreview() {
    WordleExtraTheme {
        StartGameBody({}, Modifier)
    }
}