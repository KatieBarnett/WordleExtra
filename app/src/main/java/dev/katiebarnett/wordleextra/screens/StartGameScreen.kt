@file:OptIn(ExperimentalMaterial3Api::class)

package dev.katiebarnett.wordleextra.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
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