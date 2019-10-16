package org.rubygarage.sharedelementtransitions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.rubygarage.sharedelementtransitions.databinding.FragmentEntryBinding

class EntryFragment : Fragment() {

    private lateinit var binding: FragmentEntryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEntryBinding.inflate(inflater, container, false)
        binding.clickListener = View.OnClickListener { view ->
            val direction = when (view.id) {
                R.id.simpleButton ->
                    EntryFragmentDirections.actionEntryFragmentToFirstExampleFragment()
                R.id.recyclerButton ->
                    EntryFragmentDirections.actionEntryFragmentToFirstRecyclerExampleFragment()
                R.id.viewPagerButton ->
                    EntryFragmentDirections.actionEntryFragmentToFirstPagerExampleFragment()
                else -> null
            }
            direction?.let { findNavController().navigate(it) }
        }
        binding.recyclerListener = View.OnClickListener {
            findNavController()
                .navigate(EntryFragmentDirections.actionEntryFragmentToFirstRecyclerExampleFragment())
        }
        return binding.root
    }
}