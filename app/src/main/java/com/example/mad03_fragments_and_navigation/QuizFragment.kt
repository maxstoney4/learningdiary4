package com.example.mad03_fragments_and_navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mad03_fragments_and_navigation.databinding.FragmentQuizBinding
import com.example.mad03_fragments_and_navigation.models.QuestionCatalogue


class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private lateinit var viewModel: QuizViewModel
    private val questions =
        QuestionCatalogue().defaultQuestions    // get a list of questions for the game
    //private var score = 0                                           // save the user's score
    //private var index = 0                                          // index for question data to show

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)

        Log.i("QuizFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        binding.index = viewModel.index
        binding.questionsCount = questions.size
        binding.question = questions[viewModel.index]

        binding.btnNext.setOnClickListener {
            nextQuestion()
        }

        return binding.root
    }

    private fun nextQuestion() {
        // get selected answer
            val checkedId = binding.answerBox.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.answer1 -> answerIndex = 0
                    R.id.answer2 -> answerIndex = 1
                    R.id.answer3 -> answerIndex = 2
                    R.id.answer4 -> answerIndex = 3
                }

                    if (questions[viewModel.index].answers[answerIndex].isCorrectAnswer){                     // check if is correct answer  CORRECT ANSWER
                        viewModel.score++                                                                      // update score
                    }

                     viewModel.index++


                    val test: TextView = binding.currentQuestionText
                    test.text = "Question "+viewModel.index.toString()+"/"+questions.size

                    if (viewModel.index>questions.lastIndex){                                                 //no question left
                        //view.findNavController().navigate(R.id.action_quizFragment_to_quizEndFragment)
                        val action = QuizFragmentDirections.actionQuizFragmentToQuizEndFragment(viewModel.score,questions.size)
                        this.findNavController().navigate(action)
                    } else {                                                                        //there are still questions left
                        binding.question = questions[viewModel.index]
                    }
            }
                // check if there are any questions left
                // show next question OR
                // navigate to QuizEndFragment

    }
}