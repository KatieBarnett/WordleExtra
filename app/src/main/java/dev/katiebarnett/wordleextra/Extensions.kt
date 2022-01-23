package dev.katiebarnett.wordleextra

import androidx.compose.runtime.snapshots.SnapshotStateList

fun <T> SnapshotStateList<T>.replace(newList: List<T>) {
    this.clear()
    this.addAll(newList)
}