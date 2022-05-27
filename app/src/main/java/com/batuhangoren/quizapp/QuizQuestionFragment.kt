package com.batuhangoren.quizapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.batuhangoren.quizapp.databinding.FragmentQuizQuestionBinding
import com.batuhangoren.quizapp.databinding.FragmentWelcomeBinding

class QuizQuestionFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentQuizQuestionBinding? = null
    private val binding get() = _binding!!

    private val args: QuizQuestionFragmentArgs by navArgs()

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswer : Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizQuestionBinding.inflate(inflater, container, false)

        mQuestionsList = Constants.questions()

        setQuestion()

        binding.optionOne.setOnClickListener(this)
        binding.optionTwo.setOnClickListener(this)
        binding.optionThree.setOnClickListener(this)
        binding.optionFour.setOnClickListener(this)
        binding.buttonSubmit.setOnClickListener(this)


        return binding.root
    }

    private fun setQuestion() {

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size)
            binding.buttonSubmit.text = "FINISH"
        else
            binding.buttonSubmit.text = "SUBMIT"

        val question: Question = mQuestionsList!![mCurrentPosition - 1]

        val progressText = "$mCurrentPosition/${binding.progressBar.max}"

        binding.questionText.text = question.question
        binding.flagImage.setImageResource(question.image)

        binding.progressBar.progress = mCurrentPosition
        binding.progressText.text = progressText

        binding.optionOne.text = question.option1
        binding.optionTwo.text = question.option2
        binding.optionThree.text = question.option3
        binding.optionFour.text = question.option4
    }

    override fun onClick(view: View?) {

        when(view?.id){

            R.id.optionOne ->{
                selectedOptionView(binding.optionOne,1)
            }
            R.id.optionTwo ->{
                selectedOptionView(binding.optionTwo,2)
            }
            R.id.optionThree ->{
                selectedOptionView(binding.optionThree,3)
            }
            R.id.optionFour ->{
                selectedOptionView(binding.optionFour,4)
            }
            R.id.buttonSubmit ->{
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionsList!!.size -> setQuestion()

                        else ->{

                            val action =
                                QuizQuestionFragmentDirections.
                                actionQuizQuestionFragmentToResultFragment(args.name,mCorrectAnswer, mQuestionsList!!.size)
                            findNavController().navigate(action)
                        }
                    }
                }else{
                    val question = mQuestionsList?.get(mCurrentPosition - 1 )
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                         answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList!!.size){
                        binding.buttonSubmit.text = "FINISH"
                    }else{
                        binding.buttonSubmit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){

        when(answer){
            1->{
                binding.optionOne.background = this.context?.let {
                    ContextCompat.getDrawable(it, drawableView)
                }
            }
            2->{
                binding.optionTwo.background = this.context?.let {
                    ContextCompat.getDrawable(it, drawableView)
                }
            }
            3->{
                binding.optionThree.background = this.context?.let {
                    ContextCompat.getDrawable(it, drawableView)
                }
            }
            4->{
                binding.optionFour.background = this.context?.let {
                    ContextCompat.getDrawable(it, drawableView)
                }
            }
        }
    }

    private fun selectedOptionView(textView: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        textView.setTextColor(
            Color.parseColor("#363A43")
        )
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = this.context?.let {
            ContextCompat.getDrawable(it,
                R.drawable.selected_option_border_bg)
        }
    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()

        options.add(0,binding.optionOne)
        options.add(1,binding.optionTwo)
        options.add(2,binding.optionThree)
        options.add(3,binding.optionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = this.context?.let {
                ContextCompat.getDrawable(
                    it,
                    R.drawable.default_option_border_bg
                )
            }
        }
    }


}