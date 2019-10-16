package org.rubygarage.sharedelementtransitions.pager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import org.rubygarage.sharedelementtransitions.databinding.ListItemImageBinding
import org.rubygarage.sharedelementtransitions.entity.Image
import org.rubygarage.sharedelementtransitions.extension.toTransitionGroup

class ImageListAdapter(private val itemWidth: Int) :
    RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    private val list = mutableListOf<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageViewHolder(
            ListItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val clickListener = View.OnClickListener { view ->
            val action = FirstPagerExampleFragmentDirections.actionFirstPagerExampleFragmentToSecondPagerExampleFragment(
                list.toTypedArray(),
                position
            )
            val extras = FragmentNavigatorExtras(view.toTransitionGroup())
            view.findNavController().navigate(action, extras)
        }
        holder.bind(list[position], itemWidth, clickListener)
    }

    fun submitList(list: List<Image>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class ImageViewHolder(private val binding: ListItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: Image, itemWidth: Int, onClickListener: View.OnClickListener) {
            val layoutParams = FrameLayout.LayoutParams(binding.image.layoutParams)
            val ratio = obj.height / obj.width
            layoutParams.width = itemWidth
            layoutParams.height = (itemWidth * ratio).toInt()
            binding.image.layoutParams = layoutParams
            binding.url = obj.url
            binding.position = adapterPosition
            binding.onClickListener = onClickListener
            binding.executePendingBindings()
        }
    }
}