package dev.katiebarnett.wordleextra

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.katiebarnett.wordleextra.data.WordRepository
import dev.katiebarnett.wordleextra.models.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : ViewModel() {

    var guesses = mutableStateListOf<Guess>()
        private set

    var keyboardRow1 = mutableStateListOf<Key>()
        private set

    var keyboardRow2 = mutableStateListOf<Key>()
        private set

    var keyboardRow3 = mutableStateListOf<Key>()
        private set

    var wordLength = 5

    var targetWord = ""

    var wordList: List<String> = listOf()

    var winGameAction: (() -> Unit)? = null

    fun reset(context: Context) {
        viewModelScope.launch {
            @Suppress("BlockingMethodInNonBlockingContext")
            wordList = wordRepository.getWordList(context, wordLength)
            // TODO Handle exception
            if (wordList.isEmpty()) {
                // TODO Handle error
            }
            getTargetWord()
            guesses.replace(listOf(getEmptyGuess()))
            keyboardRow1.replace(initialKeyboardRow1)
            keyboardRow2.replace(initialKeyboardRow2)
            keyboardRow3.replace(initialKeyboardRow3)
        }
    }

    fun getTargetWord() {
        val position = Random.nextInt(0, wordList.size)
        targetWord = wordList[position]
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
            updateKeyboard(lettersWithState)
            winGameAction?.invoke()
        } else if (!checkGuessIsInList(guesses.last())) {
            // TODO show error not in list
        } else {
            guesses.add(getEmptyGuess())
            updateKeyboard(lettersWithState)
        }
    }

    fun checkGuessIsInList(guess: Guess): Boolean {
        return wordList.contains(guess.asString)
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