package dev.katiebarnett.wordleextra.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.katiebarnett.wordleextra.ui.theme.WordleExtraTheme

@Composable
fun Heading(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(text = "A Jetpack Compose Experiment", style = MaterialTheme.typography.bodySmall, modifier = modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun HeadingPreview() {
    WordleExtraTheme {
        Surface {
            Heading()
        }
    }
}