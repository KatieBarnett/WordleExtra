package dev.katiebarnett.wordleextra

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.katiebarnett.wordleextra.ui.theme.WordleExtraTheme

@Composable
fun Heading() {
    Column {
        Text(text = "A Jetpack Compose Experiment", style = MaterialTheme.typography.bodySmall)
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