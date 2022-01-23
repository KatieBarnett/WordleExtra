package dev.katiebarnett.wordleextra

import dev.katiebarnett.wordleextra.models.Letter
import dev.katiebarnett.wordleextra.models.Unknown


val initialKeyboardRow1: List<Letter>
    get() {
        val list = mutableListOf<Letter>()
        "qwertyuiop".forEach {
            list.add(Unknown(it))
        }
        return list.toList()
    }

val initialKeyboardRow2: List<Letter>
    get() {
        val list = mutableListOf<Letter>()
        "asdfghjkl".forEach {
            list.add(Unknown(it))
        }
        return list.toList()
    }

val initialKeyboardRow3: List<Letter>
    get() {
        val list = mutableListOf<Letter>()
        "zxcvbnm".forEach {
            list.add(Unknown(it))
        }
        return list.toList()
    }