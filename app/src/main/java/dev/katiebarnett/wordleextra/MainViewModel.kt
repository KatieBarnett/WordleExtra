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
        guesses.replace(listOf(getEmptyGuess()))
        keyboardRow1.replace(initialKeyboardRow1)
        keyboardRow2.replace(initialKeyboardRow2)
        keyboardRow3.replace(initialKeyboardRow3)
    }

    private fun getEmptyGuess(): Guess {
        val letters = mutableListOf<Letter>()
        for (i in 0 until wordLength) {
            letters.add(Unknown())
        }
        return Guess(letters = letters)
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
        val latestGuess = guesses.last()
        val lettersWithState = latestGuess.letters.mapIndexed { index, letter ->
            letter.char?.let { char ->
                if (char == targetWord[index]) {
                    Correct(char)
                } else if (targetWord.contains(char)) {
                    Misplaced(char)
                } else {
                    Incorrect(char)
                }
            } ?: letter
        }
        guesses[guesses.lastIndex] = Guess(lettersWithState)
        if (guesses.last().isAllCorrect) {
        } else {
            guesses.add(getEmptyGuess())
        }
        updateKeyboard(lettersWithState)
    }

    fun updateKeyboard(lettersWithState: List<Letter>) {
        keyboardRow1.replace(updateKeyboardRow(keyboardRow1, lettersWithState))
        keyboardRow2.replace(updateKeyboardRow(keyboardRow2, lettersWithState))
        keyboardRow3.replace(updateKeyboardRow(keyboardRow3, lettersWithState))
    }

    private fun updateKeyboardRow(keyboardRow: List<Key>, lettersWithState: List<Letter>): List<Key> {
        return keyboardRow.map { key ->
            if (key is Letter && (key is Unknown || key is Misplaced)) {
                lettersWithState.firstOrNull { it.char == key.char } ?: key
            } else {
                key
            }
        }
    }
}