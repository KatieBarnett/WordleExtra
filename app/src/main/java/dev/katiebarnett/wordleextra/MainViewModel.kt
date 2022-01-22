package dev.katiebarnett.wordleextra

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.katiebarnett.wordleextra.models.*

class MainViewModel: ViewModel() {

    var guesses = mutableStateListOf<Guess>()
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
        guesses.addAll(listOf(Guess(letters = firstGuess)))
    }

    fun addGuess(guess: Guess) {
        guesses.add(guess)
    }
}