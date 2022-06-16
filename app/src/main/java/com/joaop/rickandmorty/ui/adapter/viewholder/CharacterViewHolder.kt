package com.joaop.rickandmorty.ui.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.joaop.rickandmorty.databinding.ItemCharacterBinding
import com.joaop.rickandmorty.ui.extension.loadFromUrl
import com.joaop.rickandmorty.domain.entity.Character

class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemCharacterBinding by lazy {
            ItemCharacterBinding.bind(itemView)
        }

        fun bind(character: Character, clickDetail: ((Character) -> Unit)?) {
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