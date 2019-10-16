package org.rubygarage.sharedelementtransitions.pager

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.transition.TransitionInflater
import org.rubygarage.sharedelementtransitions.MainActivity
import org.rubygarage.sharedelementtransitions.R
import org.rubygarage.sharedelementtransitions.databinding.FragmentFirstPagerExampleBinding
import org.rubygarage.sharedelementtransitions.entity.Image
import org.rubygarage.sharedelementtransitions.extension.waitForTransition

class FirstPagerExampleFragment : Fragment() {

    companion object {
        private val imageList = listOf(
            Image("https://loremflickr.com/320/240?lock=1", 320f, 240f),
            Image("https://loremflickr.com/320/240?lock=2", 320f, 280f),
            Image("https://loremflickr.com/320/240?lock=3", 320f, 300f),
            Image("https://loremflickr.com/320/240?lock=4", 320f, 320f),
            Image("https://loremflickr.com/320/240?lock=5", 320f, 340f),
            Image("https://loremflickr.com/320/240?lock=6", 320f, 300f),
            Image("https://loremflickr.com/320/240?lock=7", 320f, 300f),
            Image("https://loremflickr.com/320/240?lock=8", 320f, 300f)
        )
    }

    private lateinit var binding: FragmentFirstPagerExampleBinding

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
        binding = FragmentFirstPagerExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val divider = resources.getDimensionPixelSize(R.dimen.item_divider)
        val spanCount =
            (binding.recycler.layoutManager as? StaggeredGridLayoutManager)?.spanCount ?: 1
        val width = Resources.getSystem().displayMetrics.widthPixels / spanCount - divider
        val adapter = ImageListAdapter(width)
        binding.recycler.adapter = adapter
        binding.recycler.addItemDecoration(StaggeredGridItemDecoration(divider))
        adapter.submitList(imageList)

        prepareTransitions()
        waitForTransition(binding.recycler)
    }

    private fun prepareTransitions() {
        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>,
                sharedElements: MutableMap<String, View>
            ) {
                val position = (activity as? MainActivity)?.galleryPositionData ?: -1
                val selectedViewHolder =
                    binding.recycler.findViewHolderForAdapterPosition(position)
                selectedViewHolder?.itemView?.findViewById<ImageView>(R.id.image)?.let {
                    sharedElements[names[0]] = it
                }
            }
        })
    }
}