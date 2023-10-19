package epicu.uriev.food.presentation.fragments

import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import epicu.uriev.food.R
import epicu.uriev.food.databinding.FragmentSettingsBinding
import epicu.uriev.food.viewmodel.MySettingsViewModel
import epicu.uriev.food.viewmodel.MyViewModelFactory

class MySettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var audioManager: AudioManager
    private lateinit var vibrator: Vibrator
    private lateinit var viewModel: MySettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        generateGarbage()
        return binding.root
    }
    fun generateGarbage(): String {
        val stringBuilder = StringBuilder()
        repeat(10) {
            stringBuilder.append("This is garbage. ")
        }
        12+23
        return stringBuilder.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        audioManager = requireContext().getSystemService(Context.AUDIO_SERVICE) as AudioManager
        vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(requireContext())
        )[MySettingsViewModel::class.java]

        viewModel.soundProgress.observe(viewLifecycleOwner) { progress ->
            binding.soundSeekBar.progress = progress
        }
        binding.home.setOnClickListener {
            findNavController().popBackStack(R.id.difficultyFragment, false)
        }

        viewModel.brightnessProgress.observe(viewLifecycleOwner) { progress ->
            binding.brightnessSeekBar.progress = progress
        }

        binding.soundSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.setSoundProgress(progress)
                val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
                val volume = (progress * maxVolume) / 100
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.brightnessSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.setBrightnessProgress(progress)
                val brightnessValue = progress.toFloat() / 100
                val layoutParams = activity?.window?.attributes
                layoutParams?.screenBrightness = brightnessValue
                activity?.window?.attributes = layoutParams
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}