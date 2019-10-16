package org.rubygarage.sharedelementtransitions.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import org.rubygarage.sharedelementtransitions.databinding.FragmentSecondRecyclerExampleBinding

class SecondRecyclerExampleFragment : Fragment() {

    private val args: SecondRecyclerExampleFragmentArgs by navArgs()
    private lateinit var binding: FragmentSecondRecyclerExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondRecyclerExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.user = args.user
        binding.executePendingBindings()
    }
}