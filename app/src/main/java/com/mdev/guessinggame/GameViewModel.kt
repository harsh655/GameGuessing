package com.mdev.guessinggame

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    val words = listOf("Android", "Activity", "Fragment") // List of available words
    val secretWord = words.random().uppercase()  // Randomly picked word the user has to guess
    var secretWordDisplay = ""  // How the word is displayed
    var correctGuesses = "" // Correct letters
    var incorrectGuesses = ""   // Incorrect letters
    var livesLeft = 8 // Number of lives left

    init {
        secretWordDisplay = deriveSecretWordDisplay()
    }

    fun deriveSecretWordDisplay() : String {
        var display = ""
        secretWord.forEach {
            display += checkLetter(it.toString())
        }
        return display
    }

    fun checkLetter(str: String) = when (correctGuesses.contains(str)) {
        true -> str
        false -> "_"
    }

    fun makeGuess(guess:String) {
        if(guess.length == 1) {
            if(secretWord.contains(guess)) {
                correctGuesses += guess
                secretWordDisplay = deriveSecretWordDisplay()
            } else {
                incorrectGuesses += "$guess "
                livesLeft--
            }
        }
    }

    fun isWon() = secretWord.equals(secretWordDisplay, true)

    fun isLost() = livesLeft <= 0

    fun wonLostMessage() : String {
        var message = ""
        if(isWon()) message = "You Won!"
        else if (isLost()) message = "You Lost!"

        message += " The word was $secretWord."
        return message
    }
}