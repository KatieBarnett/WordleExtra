package dev.katiebarnett.wordleextra.models

data class Guess(val letters: List<Letter>) {

    val isComplete: Boolean
        get() = letters.indexOfFirst { it is Unknown && it.char == null } == -1
}
