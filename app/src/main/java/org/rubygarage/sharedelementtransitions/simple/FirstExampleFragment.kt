package org.rubygarage.sharedelementtransitions.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import org.rubygarage.sharedelementtransitions.entity.User
import org.rubygarage.sharedelementtransitions.databinding.FragmentFirstExampleBinding
import org.rubygarage.sharedelementtransitions.extension.toTransitionGroup

class FirstExampleFragment : Fragment() {

    private lateinit var binding: FragmentFirstExampleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = User(
            300,
            "John Doe",
            "https://i.pravatar.cc/150?img=1"
        )
        binding.user = user
        binding.clickListener = View.OnClickListener {
            val direction = FirstExampleFragmentDirections
                .actionFirstExampleFragmentToSecondExampleFragment(user)
            val extras = FragmentNavigatorExtras(
                binding.name.toTransitionGroup(),
                binding.avatar.toTransitionGroup(),
                binding.description.toTransitionGroup()
            )
            findNavController().navigate(direction, extras)
        }
    }
}