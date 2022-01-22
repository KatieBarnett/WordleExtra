@file:OptIn(ExperimentalMaterial3Api::class)

package dev.katiebarnett.wordleextra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.katiebarnett.wordleextra.models.Guess
import dev.katiebarnett.wordleextra.ui.theme.WordleExtraTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordleExtraTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text(text = "Wordle Extra")}
                        )
                    }
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Heading()
                        GameContent(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun GameContent(
    viewModel: MainViewModel
) {
    Guesses(viewModel.guesses)
}

@Composable
fun Guesses(guesses: List<Guess>) {
    LazyColumn {
        items(guesses) { guess ->
            Guess(guess)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WordleExtraTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Wordle Extra")}
                )
            }) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Heading()
                GuessesPreview()
            }
        }
    }
}