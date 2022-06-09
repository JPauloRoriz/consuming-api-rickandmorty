package com.joaop.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joaop.rickandmorty.databinding.ItemCharacterBinding
import com.joaop.rickandmorty.domain.entity.Character
import com.joaop.rickandmorty.ui.extension.loadFromUrl


class CharacterAdapter : ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(COMPARATOR) {
    var clickDetail: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount() = currentList.size

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemCharacterBinding by lazy {
            ItemCharacterBinding.bind(itemView)
        }

        fun bind(character: Character) {
            with(binding) {
                btnDetail.setOnClickListener {
                    clickDetail?.invoke(character)
                }
                imgCharacter.loadFromUrl(character.image)
                titleCharacter.text = character.name
                subtitleCharacter.text = character.species

            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

}