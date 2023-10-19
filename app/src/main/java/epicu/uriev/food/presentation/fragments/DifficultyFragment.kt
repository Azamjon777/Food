package epicu.uriev.food.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import epicu.uriev.food.R
import epicu.uriev.food.databinding.FragmentDifficultyBinding
import epicu.uriev.food.viewmodel.GameViewModel

class DifficultyFragment : Fragment() {
    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentDifficultyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDifficultyBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(requireActivity())[GameViewModel::class.java]
        val radioGroup = binding.radioGroup
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioDifficulty1 -> viewModel.setDifficulty(1)
                R.id.radioDifficulty2 -> viewModel.setDifficulty(2)
                R.id.radioDifficulty3 -> viewModel.setDifficulty(3)
            }
        }

        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_difficultyFragment_to_settingsFragment)
        }

        val startButton = binding.buttonStartGame
        startButton.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.select_difficulty_level),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                findNavController().navigate(R.id.action_difficultyFragment_to_mainFragment)
            }
        }

        return view
    }
}