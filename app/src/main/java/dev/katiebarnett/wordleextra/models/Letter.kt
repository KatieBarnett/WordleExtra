package dev.katiebarnett.wordleextra.models

sealed class Key()

sealed class Letter(
    open val char: Char?
): Key()

class Correct(override val char: Char): Letter(char)
class Misplaced(override val char: Char): Letter(char)
class Incorrect(override val char: Char): Letter(char)
class Unknown(override val char: Char? = null): Letter(char)

object Backspace: Key()
object Enter: Key()