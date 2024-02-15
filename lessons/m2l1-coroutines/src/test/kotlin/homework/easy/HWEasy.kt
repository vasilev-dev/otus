package ru.otus.otuskotlin.coroutines.homework.easy

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.*
import kotlin.test.Test

class HWEasy {

    @Test
    fun easyHw(): Unit = runBlocking(Dispatchers.Default) {
        val numbers = generateNumbers()
        val toFind = 10
        val toFindOther = 1000

        val foundNumbers = listOf(
            async {
                findNumberInList(toFind, numbers)
            },
            async {
                findNumberInList(toFindOther, numbers)
            }
        ).awaitAll()

        foundNumbers.forEach {
            if (it != -1) {
                println("Your number $it found!")
            } else {
                println("Not found number $toFind || $toFindOther")
            }
        }
    }
}
