package com.example.composition.presetation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListener()
        bindViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupClickListener() {
        binding.buttonTryAgain.setOnClickListener {
            retryGame()
        }
    }

    private fun bindViews() {
        binding.gameResult = args.gameResult
        with(binding){
            emojiResult.setImageResource(getSmileResourceId())
//            tvRequiredAnswers.text = String.format(
//                getString(R.string.minimal_right_answers),
//                args.gameResult.gameSetting.minCountOfRightAnswers
//            )
//            tvScoreAnswers.text = String.format(
//                getString(R.string.your_score),
//                args.gameResult.countOfRightAnswers
//            )
//            tvRequiredPercentage.text = String.format(
//                getString(R.string.minimal_percent_of_answers),
//                args.gameResult.gameSetting.minPercentOfRightAnswers
//            )
            tvScorePercentage.text = String.format(
                getString(R.string.right_answers_percent),
                getPercentOfRightAnswers()
            )
        }
    }

    private fun getSmileResourceId() : Int {
        return if (args.gameResult.winner) {
            R.drawable.ic_smile_emoji
        } else {
            R.drawable.ic_sad_emoji
        }
    }

    private fun getPercentOfRightAnswers() = with(args.gameResult) {
        if (countOfQuestions == 0) {
            return 0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

}