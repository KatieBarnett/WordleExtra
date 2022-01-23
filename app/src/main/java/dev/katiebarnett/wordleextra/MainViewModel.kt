package dev.katiebarnett.wordleextra

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dev.katiebarnett.wordleextra.models.*

class MainViewModel: ViewModel() {

    var guesses = mutableStateListOf<Guess>()
        private set

    var keyboardRow1 = mutableStateListOf<Key>()
        private set

    var keyboardRow2 = mutableStateListOf<Key>()
        private set

    var keyboardRow3 = mutableStateListOf<Key>()
        private set

    var wordLength = 5

    var targetWord = "wince"

    init {
        reset()
    }

    fun reset() {
        val firstGuess = mutableListOf<Letter>()
        for (i in 0 until wordLength) {
            firstGuess.add(Unknown())
        }
        guesses.clear()
        guesses.addAll(listOf(Guess(letters = firstGuess)))
        keyboardRow1.clear()
        keyboardRow1.addAll(initialKeyboardRow1)
        keyboardRow2.clear()
        keyboardRow2.addAll(initialKeyboardRow2)
        keyboardRow3.clear()
        keyboardRow3.addAll(initialKeyboardRow3)
    }

    fun keyClickAction(key: Key) {
        val latestGuess = guesses.last()
        if (key is Letter) {
            val latestGuessLetters = latestGuess.letters.toMutableList()
            val index = latestGuessLetters.indexOfFirst { it is Unknown && it.char == null }
            if (index != -1) {
                latestGuessLetters[index] = Unknown(key.char)
                guesses[guesses.lastIndex] = Guess(latestGuessLetters)
            }
        } else if (key is Enter && latestGuess.isComplete) {
            submitGuess()
        } else if (key is Backspace) {
            val latestGuessLetters = latestGuess.letters.toMutableList()
            val index = latestGuessLetters.indexOfLast { it is Unknown && it.char != null }
            if (index != -1) {
                latestGuessLetters[index] = Unknown()
                guesses[guesses.lastIndex] = Guess(latestGuessLetters)
            }
        }
    }

    private fun submitGuess() {

    }

    fun addGuess(guess: Guess) {
        guesses.add(guess)
    }
}