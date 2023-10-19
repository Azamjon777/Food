package epicu.uriev.food.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MySettingsViewModel(private val context: Context) : ViewModel() {
    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    private val _soundProgress = MutableLiveData<Int>()
    private val _brightnessProgress = MutableLiveData<Int>()

    val soundProgress: LiveData<Int> = _soundProgress
    val brightnessProgress: LiveData<Int> = _brightnessProgress

    init {
        _soundProgress.value = getSavedProgress("sound_progress")
        _brightnessProgress.value = getSavedProgress("brightness_progress")
    }

    fun setSoundProgress(progress: Int) {
        _soundProgress.value = progress
        saveProgress("sound_progress", progress)
    }

    fun setBrightnessProgress(progress: Int) {
        _brightnessProgress.value = progress
        saveProgress("brightness_progress", progress)
    }

    fun resetSettingsToDefault() {
        // Сбрасываем все настройки в исходные значения
        setSoundProgress(0)
        setBrightnessProgress(0)
    }

    private fun saveProgress(key: String, progress: Int) {
        sharedPrefs.edit().putInt(key, progress).apply()
    }

    private fun getSavedProgress(key: String): Int {
        return sharedPrefs.getInt(key, 0)
    }
}
