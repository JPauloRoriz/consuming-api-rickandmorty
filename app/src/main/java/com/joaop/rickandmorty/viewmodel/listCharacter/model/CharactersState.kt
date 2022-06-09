package com.joaop.rickandmorty.viewmodel.listCharacter.model

import com.joaop.rickandmorty.domain.entity.Character

sealed class CharactersState {
    object ShowLoading : CharactersState()
    object HideLoading : CharactersState()
    data class ShowLoadingPagination(val show : Boolean) : CharactersState()
    data class OnSuccessList(val result : List<Character>) : CharactersState()
}