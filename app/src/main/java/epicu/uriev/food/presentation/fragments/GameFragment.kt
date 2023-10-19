package epicu.uriev.food.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import epicu.uriev.food.R
import epicu.uriev.food.adapter.ImageAdapter
import epicu.uriev.food.databinding.CustomDialogBinding
import epicu.uriev.food.databinding.FragmentGameBinding
import epicu.uriev.food.viewmodel.GameViewModel

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var imageAdapter: ImageAdapter

    private var currentFruitId = R.drawable.fruit_0
    private var score = 0
    private var bestScore = 0

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var timer: CountDownTimer

    private lateinit var imageIds: MutableList<Int>

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(requireActivity())[GameViewModel::class.java]

        sharedPreferences =
            requireContext().getSharedPreferences("game_prefs", Context.MODE_PRIVATE)

        bestScore = sharedPreferences.getInt(
            getString(R.string.best_score) + "${viewModel.selectedDifficulty.value}",
            0
        )
        binding.bestScoreTextView.text = getString(R.string.best_score) + "$bestScore"
        val difficultyTextView = binding.difficultyTextView

        viewModel.selectedDifficulty.observe(viewLifecycleOwner) { difficultyLevel ->
            difficultyTextView.text = "lv: $difficultyLevel"
        }
        imageIds = mutableListOf()
        for (i in 0 until 9) {
            imageIds.add(R.drawable.fruit_0)
            imageIds.add(R.drawable.fruit_1)
            imageIds.add(R.drawable.fruit_2)
            imageIds.add(R.drawable.fruit_3)
            imageIds.add(R.drawable.fruit_4)
            imageIds.add(R.drawable.fruit_5)
            imageIds.add(R.drawable.fruit_6)
            imageIds.add(R.drawable.fruit_7)
            imageIds.add(R.drawable.fruit_8)
            imageIds.add(R.drawable.fruit_9)
        }
        imageIds.shuffle()

        imageAdapter = ImageAdapter(requireContext(), imageIds)
        binding.gridView.adapter = imageAdapter

        binding.gridView.setOnItemClickListener { _, _, position, _ ->
            val selectedFruitId = imageAdapter.getItem(position)
            if (selectedFruitId == currentFruitId) {
                score++
                binding.scoreTextView.text = getString(R.string.score) + "$score"
            }
        }
        val timerInterval = when (viewModel.selectedDifficulty.value ?: 1) {
            1 -> 2000L
            2 -> 1500L
            3 -> 1000L
            else -> 2000L
        }

        binding.home.setOnClickListener {
            findNavController().popBackStack(R.id.difficultyFragment, false)
        }

        timer = object : CountDownTimer(10000, timerInterval) {
            override fun onTick(millisUntilFinished: Long) {

                if (imageIds.isNotEmpty()) {
                    currentFruitId = imageIds.random()
                    binding.currentFruitImageView.setImageResource(currentFruitId)
                }
            }

            override fun onFinish() {
                if (score > bestScore) {
                    bestScore = score
                    val editor = sharedPreferences.edit()
                    editor.putInt(
                        getString(R.string.best_score) + "${viewModel.selectedDifficulty.value}",
                        bestScore
                    )
                    editor.apply()
                    binding.bestScoreTextView.text = getString(R.string.best_score) + "$bestScore"
                }

                showResultsDialog()
            }
        }
        timer.start()

        return view
    }

    private fun showResultsDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val bindingCustomDialogBinding = CustomDialogBinding.inflate(layoutInflater)
        builder.setView(bindingCustomDialogBinding.root)
        val alertDialog = builder.create()

        bindingCustomDialogBinding.dialogTitle.text = getString(R.string.game_over)
        bindingCustomDialogBinding.dialogMessage.text =
            getString(R.string.your_score) + "$score\n\n\n" + getString(R.string.best_score) + "$bestScore"

        bindingCustomDialogBinding.restartButton.setOnClickListener {
            score = 0
            binding.scoreTextView.text = getString(R.string.score_0)

            imageIds.shuffle()

            val newImageAdapter = ImageAdapter(requireContext(), imageIds)
            binding.gridView.adapter = newImageAdapter

            timer.cancel()
            timer.start()

            alertDialog.dismiss()
        }

        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }
}