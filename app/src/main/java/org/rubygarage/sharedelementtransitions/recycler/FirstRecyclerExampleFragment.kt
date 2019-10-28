package org.rubygarage.sharedelementtransitions.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.rubygarage.sharedelementtransitions.R
import org.rubygarage.sharedelementtransitions.databinding.FragmentFirstRecyclerExampleBinding
import org.rubygarage.sharedelementtransitions.entity.User
import org.rubygarage.sharedelementtransitions.extension.waitForTransition

class FirstRecyclerExampleFragment : Fragment() {

    private lateinit var binding: FragmentFirstRecyclerExampleBinding
    private var adapter = UserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstRecyclerExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter
        adapter.submitList(getFakeUserList())
        waitForTransition(binding.recycler)
    }

    private fun getFakeUserList(): List<User> {
        return resources.getStringArray(R.array.user_details_array).mapIndexed { index, details ->
            User(
                index,
                "User $index",
                details,
                "https://i.pravatar.cc/150?img=$index"
            )
        }
    }
}