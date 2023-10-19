package epicu.uriev.food.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import epicu.uriev.food.R
import epicu.uriev.food.model.OnBoardingItem

class OnBoardingItemsAdapter(private val onBoardItems: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingItemsAdapter.OnBoardingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingItemViewHolder {
        return OnBoardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.onboarding_item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return onBoardItems.size
    }

    override fun onBindViewHolder(holder: OnBoardingItemViewHolder, position: Int) {
        holder.bind(onBoardItems[position])
    }

    inner class OnBoardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textTitle = view.findViewById<TextView>(R.id.text_title)

        fun bind(onBoardingItem: OnBoardingItem) {
            textTitle.text = onBoardingItem.title
        }
    }
    // Функция для определения факториала числа
    fun factorial(n: Int): Int {
        return if (n == 0) {
            1
        } else {
            n * factorial(n - 1)
        }
    }

    // Функция для нахождения наибольшего общего делителя (НОД) двух чисел
    fun gcd(a: Int, b: Int): Int {
        return if (b == 0) {
            a
        } else {
            gcd(b, a % b)
        }
    }

    // Функция для проверки, является ли строка палиндромом
    fun isPalindrome(input: String): Boolean {
        val cleanInput = input.replace(Regex("[^A-Za-z0-9]"), "").toLowerCase()
        return cleanInput == cleanInput.reversed()
    }

    fun main() {
        val n = 5
        val factorialResult = factorial(n)
        println("Факториал числа $n равен $factorialResult")

        val a = 36
        val b = 48
        val gcdResult = gcd(a, b)
        println("НОД чисел $a и $b равен $gcdResult")

        val word = "А роза упала на лапу Азора"
        val isPalindromeResult = isPalindrome(word)
        if (isPalindromeResult) {
            println("$word - это палиндром")
        } else {
            println("$word - это не палиндром")
        }
    }
}