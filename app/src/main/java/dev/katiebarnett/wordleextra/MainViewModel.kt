package dev.katiebarnett.wordleextra

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.katiebarnett.wordleextra.models.Correct
import dev.katiebarnett.wordleextra.models.Guess
import dev.katiebarnett.wordleextra.models.Incorrect
import dev.katiebarnett.wordleextra.models.Misplaced

class MainViewModel: ViewModel() {

    var guesses = mutableStateListOf<Guess>()
        private set

    init {
        // Temp
        guesses.addAll(
            listOf(
                Guess(
                    listOf(
                        Incorrect('g'),
                        Incorrect('r'),
                        Misplaced('e'),
                        Incorrect('a'),
                        Incorrect('t')
                    )
                ),
                Guess(
                    listOf(
                        Incorrect('l'),
                        Correct('i'),
                        Misplaced('v'),
                        Misplaced('e'),
                        Incorrect('d')
                    )
                ),
                Guess(
                    listOf(
                        Correct('w'),
                        Correct('i'),
                        Correct('n'),
                        Correct('c'),
                        Correct('e')
                    )
                )
            )
        )
    }

    fun addGuess(guess: Guess) {
        guesses.add(guess)
    }
}