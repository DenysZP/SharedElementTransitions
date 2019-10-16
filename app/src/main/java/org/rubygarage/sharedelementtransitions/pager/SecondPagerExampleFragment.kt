package org.rubygarage.sharedelementtransitions.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.SharedElementCallback
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import androidx.viewpager2.widget.ViewPager2
import org.rubygarage.sharedelementtransitions.MainActivity
import org.rubygarage.sharedelementtransitions.R
import org.rubygarage.sharedelementtransitions.databinding.FragmentSecondPagerExampleBinding

class SecondPagerExampleFragment : Fragment() {

    private val args: SecondPagerExampleFragmentArgs by navArgs()
    private lateinit var binding: FragmentSecondPagerExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.image_shared_element_transition)
        prepareTransitions()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondPagerExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = GalleryAdapter(object : GalleryAdapter.OnImageReadyListener {
            override fun onImageReady(position: Int) {
                if (position == args.position) {
                    startPostponedEnterTransition()
                }
            }
        })
        adapter.targetPosition = args.position
        adapter.submitList(args.images.toList())
        binding.pager.adapter = adapter
        postponeEnterTransition()
        binding.pager.doOnPreDraw {
            binding.pager.setCurrentItem(args.position, false)
        }
        binding.pager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    (activity as? MainActivity)?.galleryPositionData = position
                }
            })
    }

    private fun prepareTransitions() {
        setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>,
                sharedElements: MutableMap<String, View>
            ) {
                val position = (activity as? MainActivity)?.galleryPositionData ?: -1
                binding.pager.findViewWithTag<ViewGroup>(position)
                    ?.findViewById<ImageView>(R.id.image)?.let { sharedElements[names[0]] = it }
            }
        })
    }
}