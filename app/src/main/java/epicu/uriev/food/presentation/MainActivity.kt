package epicu.uriev.food.presentation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import epicu.uriev.food.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            generateRandomNumbersAsync()
            repeatTextAsync("", 1)
        }
        loadAndDisplayImage(this, "")
    }

    fun loadAndDisplayImage(context: Context, imageUrl: String) {
        try {
            Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.img)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun generateRandomNumbersAsync(): List<Int> {
        return runBlocking {
            delay(10) // Подождем 1 секунду
            return@runBlocking List(10) { (0..100).random() }
        }
    }

    suspend fun repeatTextAsync(text: String, times: Int): String {
        return runBlocking {
            delay(10) // Подождем 1 секунду
            return@runBlocking buildString {
                repeat(times) {
                    append(text)
                }
            }
        }
    }
}