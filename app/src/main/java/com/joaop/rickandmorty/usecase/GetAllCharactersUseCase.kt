package com.joaop.rickandmorty.usecase

import com.joaop.rickandmorty.data.callback.OnResultListener
import com.joaop.rickandmorty.data.entities.response.BaseResponse
import com.joaop.rickandmorty.domain.entity.Character
import com.joaop.rickandmorty.data.repository.CharacterRepository

class GetAllCharactersUseCase(
    private val repository: CharacterRepository
) {
   operator fun invoke(page : Int, listener: OnResultListener<BaseResponse<List<Character>>?>) {
        repository.getCharacters(page, listener)
    }
}