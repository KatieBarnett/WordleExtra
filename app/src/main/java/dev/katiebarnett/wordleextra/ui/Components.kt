package dev.katiebarnett.wordleextra.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

@Composable
fun SnackbarScreen(snackbarHostState: SnackbarHostState, modifier: Modifier) {
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom))
}