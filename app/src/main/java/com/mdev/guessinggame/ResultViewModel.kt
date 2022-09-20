package com.mdev.guessinggame

import androidx.lifecycle.ViewModel

class ResultViewModel(finalResult: String) : ViewModel() {
    val result = finalResult
}