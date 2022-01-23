package dev.katiebarnett.wordleextra

import dev.katiebarnett.wordleextra.models.*


val initialKeyboardRow1: List<Key>
    get() {
        val list = mutableListOf<Key>()
        "qwertyuiop".forEach {
            list.add(Unknown(it))
        }
        return list.toList()
    }

val initialKeyboardRow2: List<Key>
    get() {
        val list = mutableListOf<Key>()
        "asdfghjkl".forEach {
            list.add(Unknown(it))
        }
        return list.toList()
    }

val initialKeyboardRow3: List<Key>
    get() {
        val list = mutableListOf<Key>()
        list.add(Enter)
        "zxcvbnm".forEach {
            list.add(Unknown(it))
        }
        list.add(Backspace)
        return list.toList()
    }