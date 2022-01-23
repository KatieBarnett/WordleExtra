package dev.katiebarnett.wordleextra

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dev.katiebarnett.wordleextra.models.Guess
import dev.katiebarnett.wordleextra.models.Letter
import dev.katiebarnett.wordleextra.models.Unknown

class MainViewModel: ViewModel() {

    var guesses = mutableStateListOf<Guess>()
        private set

    var keyboardRow1 = mutableStateListOf<Letter>()
        private set

    var keyboardRow2 = mutableStateListOf<Letter>()
        private set

    var keyboardRow3 = mutableStateListOf<Letter>()
        private set

    var wordLength = 5

    init {
        reset()
    }

    fun reset() {
        val firstGuess = mutableListOf<Letter>()
        for (i in 0..wordLength) {
            firstGuess.add(Unknown())
        }
        guesses.clear()
        guesses.addAll(listOf(Guess(letters = firstGuess)))
        keyboardRow1.clear()
        keyboardRow1.addAll(initialKeyboardRow1)
        keyboardRow1.clear()
        keyboardRow1.addAll(initialKeyboardRow2)
        keyboardRow1.clear()
        keyboardRow1.addAll(initialKeyboardRow3)
    }

    fun addGuess(guess: Guess) {
        guesses.add(guess)
    }
}