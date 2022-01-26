package dev.katiebarnett.wordleextra

import android.content.Context
import android.content.res.AssetManager.ACCESS_BUFFER
import android.content.res.AssetManager.ACCESS_STREAMING
import android.os.ParcelFileDescriptor.open
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.nio.channels.AsynchronousFileChannel.open
import java.nio.channels.FileChannel.open
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordRepository @Inject constructor(
) {

    @Suppress("BlockingMethodInNonBlockingContext")
    @Throws(IOException::class)
    suspend fun getWordList(context: Context, wordLength: Int, language: Language = Language.ENGLISH): List<String> {
        return withContext(Dispatchers.IO) {
            val wordList = mutableListOf<String>()
            try {
                val inputStream = context.assets.open(language.folderName + "/words_" + wordLength + "_letters.txt")
                inputStream.bufferedReader().useLines { lines ->
                    lines.forEach {
                        wordList.add(it)
                    }
                }
            } catch (e: IOException) {
                throw e
            }
            wordList
        }
    }
}
