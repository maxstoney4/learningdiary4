package com.example.mad03_fragments_and_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.mad03_fragments_and_navigation.databinding.FragmentQuizEndBinding


class QuizEndFragment : Fragment() {
    private lateinit var binding: FragmentQuizEndBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_end, container, false)

        // get score from navigation arguments

        val args = QuizEndFragmentArgs.fromBundle(requireArguments())

        // show score
        val test: TextView = binding.textView7
        test.text= args.score.toString()+"/"+args.maxNum.toString()

        binding.restart.setOnClickListener {
            test.text = "btn clicked!"
            this.findNavController().navigate(R.id.action_quizEndFragment_to_quizFragment)
        }

        return binding.root
    }
}