package dev.katiebarnett.wordleextra

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import dev.katiebarnett.wordleextra.models.*
import dev.katiebarnett.wordleextra.ui.theme.*

fun getFontSizeForBoxSize(boxSize: Dp): TextUnit {
    return (boxSize.value * 0.8).sp
}

fun Key.getBackgroundColor(): Color {
    return when(this) {
        is Incorrect -> Comment
        is Correct -> Green
        is Misplaced -> Orange
        is Unknown -> Foreground
        Backspace -> Foreground
        Enter -> Foreground
    }
}

fun Key.getForegroundColor(): Color {
    return when(this) {
        is Incorrect -> Foreground
        is Correct -> Foreground
        is Misplaced -> Foreground
        is Unknown -> Surface
        Backspace -> Surface
        Enter -> Surface
    }
}