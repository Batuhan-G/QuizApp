package com.batuhangoren.quizapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.batuhangoren.quizapp.databinding.FragmentQuizQuestionBinding
import com.batuhangoren.quizapp.databinding.FragmentWelcomeBinding

class QuizQuestionFragment : Fragment() {

    private var _binding: FragmentQuizQuestionBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizQuestionBinding.inflate(inflater, container, false)

        val questionsList = Constants.questions()
        Log.e("Question Size","${questionsList.size}")

        for (i in questionsList){
            Log.e("Questions",i.question)
        }

        return binding.root
    }
}