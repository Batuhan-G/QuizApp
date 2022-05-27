package com.batuhangoren.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.batuhangoren.quizapp.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        binding.startButton.setOnClickListener {

            binding.nameEditText.text.let {
                if (it.toString().isNotEmpty()){
                    val action =
                        WelcomeFragmentDirections.
                        actionWelcomeFragmentToQuizQuestionFragment(
                            binding.nameEditText.text.toString())
                    findNavController().navigate(action)
                }else
                    Toast.makeText(this.context,"Please enter a name.",Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }
}