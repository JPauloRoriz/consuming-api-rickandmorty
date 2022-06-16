package com.joaop.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.joaop.rickandmorty.databinding.ItemCharacterBinding
import com.joaop.rickandmorty.domain.entity.Character
import com.joaop.rickandmorty.ui.adapter.viewholder.CharacterViewHolder


class CharacterAdapter : ListAdapter<Character, CharacterViewHolder>(COMPARATOR) {
    var clickDetail: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(currentList[position], clickDetail)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }


    override fun submitList(list: List<Character>?) {
        list?.toMutableList()?.let {
            super.submitList(ArrayList(it))
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }
}