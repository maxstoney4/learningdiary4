package com.example.mad03_fragments_and_navigation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    init {
        Log.i("GameViewModel", "QuizViewModel created!")
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("QuizViewModel", "QuizViewModel destroyed!")
    }
    var score = 0
    var index = 0

    /*
    val currentIndex: MutableLiveData<Int> by lazy{
        MutableLiveData<Int>()
    }
     */

}