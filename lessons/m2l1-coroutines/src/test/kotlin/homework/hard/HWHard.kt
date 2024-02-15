package ru.otus.otuskotlin.coroutines.homework.hard

import kotlinx.coroutines.*
import ru.otus.otuskotlin.coroutines.homework.hard.dto.Dictionary
import java.io.File
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.test.Test

class HWHard {
    @Test
    fun hardHw(): Unit = runBlocking {
        val dictionaryApi = DictionaryApi()
        val words = FileReader.readFile().split(" ", "\n").toSet()

        val dictionaries = findWords(dictionaryApi, words, Locale.EN)
        println(dictionaries.count())

        dictionaries.filterNotNull().map { dictionary ->
            print("For word ${dictionary.word} i found examples: ")
            println(
                dictionary.meanings
                    .mapNotNull { definition ->
                        val r = definition.definitions
                            .mapNotNull { it.example.takeIf { it?.isNotBlank() == true } }
                            .takeIf { it.isNotEmpty() }
                        r
                    }
                    .takeIf { it.isNotEmpty() }
            )
        }
    }

    private suspend fun findWords(
        dictionaryApi: DictionaryApi,
        words: Set<String>,
        @Suppress("SameParameterValue") locale: Locale
    ): List<Dictionary?> = coroutineScope {
        val list = mutableListOf<Dictionary?>()

        words.forEach {
            launch(Dispatchers.IO) {
                try {
                    list.add(dictionaryApi.findWord(locale, it))
                }
                catch (ex: Exception) {
                    println(ex.message)
                }
            }
        }

        list
    }


    object FileReader {
        fun readFile(): String =
            File(
                this::class.java.classLoader.getResource("words.txt")?.toURI()
                    ?: throw RuntimeException("Can't read file")
            ).readText()
    }
}
