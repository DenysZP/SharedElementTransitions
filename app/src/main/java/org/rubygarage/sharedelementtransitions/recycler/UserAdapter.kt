package org.rubygarage.sharedelementtransitions.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import org.rubygarage.sharedelementtransitions.entity.User
import org.rubygarage.sharedelementtransitions.databinding.ListItemUserBinding
import org.rubygarage.sharedelementtransitions.extension.toTransitionGroup

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val items = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ListItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun submitList(list: List<User>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class UserViewHolder(private val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.clickListener = View.OnClickListener {
                val destination = FirstRecyclerExampleFragmentDirections
                    .actionFirstRecyclerExampleFragmentToSecondRecyclerExampleFragment(user)
                val extras = FragmentNavigatorExtras(
                    binding.name.toTransitionGroup(),
                    binding.avatar.toTransitionGroup(),
                    binding.description.toTransitionGroup()
                )
                it.findNavController().navigate(destination, extras)
            }
            binding.user = user
            binding.executePendingBindings()
        }
    }
}