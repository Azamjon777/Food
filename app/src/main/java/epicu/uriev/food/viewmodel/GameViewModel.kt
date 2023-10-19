package epicu.uriev.food.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val _selectedDifficulty = MutableLiveData<Int>()
    val selectedDifficulty: LiveData<Int>
        get() = _selectedDifficulty

    fun setDifficulty(difficulty: Int) {
        _selectedDifficulty.value = difficulty
    }
}