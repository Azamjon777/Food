package epicu.uriev.food.presentation

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import epicu.uriev.food.R
import epicu.uriev.food.adapter.OnBoardingItemsAdapter
import epicu.uriev.food.model.OnBoardingItem

class On1BoardingActivity : AppCompatActivity() {
    private lateinit var onBoardingItemsAdapter: OnBoardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        setOnBoardingItems()
        setupIndicators()
        setCurrentIndicator(0)
        main()
    }

    private fun setOnBoardingItems() {
        onBoardingItemsAdapter = OnBoardingItemsAdapter(
            listOf(
                OnBoardingItem(
                    getString(R.string.shop_by_game),
                ),
                OnBoardingItem(
                    getString(R.string.the_best_score),
                ),
            )
        )
        val onBoardingViewPager = findViewById<ViewPager2>(R.id.onboard_view_pager)
        onBoardingViewPager.adapter = onBoardingItemsAdapter
        onBoardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onBoardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_IF_CONTENT_SCROLLS
        findViewById<AppCompatButton>(R.id.btn_next).setOnClickListener {
            if (onBoardingViewPager.currentItem + 1 < onBoardingItemsAdapter.itemCount) {
                onBoardingViewPager.currentItem += 1
            } else {
                navigateToHomeActivity()
            }
        }
        findViewById<TextView>(R.id.skip).setOnClickListener {
            navigateToHomeActivity()
        }
    }

    private fun navigateToHomeActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun setupIndicators() {
        indicatorsContainer = findViewById(R.id.indicatorContainer)
        val indicators = arrayOfNulls<ImageView>(onBoardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)

            }
        }
    }

    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }

    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun printMessage(message: String) {
        println(message)
    }

    fun greet(name: String = "Гость", greeting: String = "Привет") {
        println("$greeting, $name!")
    }

    fun isEven(number: Int): Boolean = number % 2 == 0

    fun sum(vararg numbers: Int): Int {
        var result = 0
        for (number in numbers) {
            result += number
        }
        return result
    }

    // Функция для определения, является ли число простым
    fun isPrime(number: Int): Boolean {
        if (number <= 1) return false
        for (i in 2 until number) {
            if (number % i == 0) {
                return false
            }
        }
        return true
    }

    // Функция для вычисления суммы всех чисел в заданном диапазоне
    fun sumInRange(start: Int, end: Int): Int {
        var sum = 0
        for (i in start..end) {
            sum += i
        }
        return sum
    }

    // Функция для нахождения максимального элемента в списке
    fun findMax(numbers: List<Int>): Int {
        if (numbers.isEmpty()) {
            throw IllegalArgumentException("Список чисел пуст")
        }
        var max = numbers[0]
        for (number in numbers) {
            if (number > max) {
                max = number
            }
        }
        return max
    }

    fun main() {
        val sumResult = add(5, 3)
        println("Сумма: $sumResult")

        printMessage("Привет, мир!")

        greet("Анна")
        greet("Петр", "Здравствуйте")

        val number = 7
        if (isEven(number)) {
            println("$number - четное число")
        } else {
            println("$number - нечетное число")
        }

        val total = sum(1, 2, 3, 4, 5)
        println("Сумма чисел: $total")
        val numberToCheck = 17
        val isPrimeResult = isPrime(numberToCheck)
        if (isPrimeResult) {
            println("$numberToCheck - это простое число")
        } else {
            println("$numberToCheck - это не простое число")
        }

        val startRange = 1
        val endRange = 10
        println("Сумма чисел от $startRange до $endRange равна $sumResult")

        val numbersList = listOf(15, 7, 23, 42, 9, 12, 5)
        val maxNumber = findMax(numbersList)
        println("Максимальное число в списке: $maxNumber")
    }
}