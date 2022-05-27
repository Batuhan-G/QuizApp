package com.batuhangoren.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.batuhangoren.quizapp.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentResultBinding.inflate(inflater, container, false)

        binding.congradName.text = args.name

        val totalQuestion = args.totalQuestion
        val correctAnswer = args.correctAnswer

        val scoreText = "Your Score is $correctAnswer out of $totalQuestion"
        binding.scoreText.text = scoreText

        binding.buttonFinish.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToWelcomeFragment()
                findNavController().navigate(action)
        }
        return binding.root
    }

}