package dev.katiebarnett.wordleextra.models

sealed class Letter(
    open val char: Char?
)

class Correct(override val char: Char): Letter(char)
class Misplaced(override val char: Char): Letter(char)
class Incorrect(override val char: Char): Letter(char)
class Unknown(override val char: Char? = null): Letter(char)
