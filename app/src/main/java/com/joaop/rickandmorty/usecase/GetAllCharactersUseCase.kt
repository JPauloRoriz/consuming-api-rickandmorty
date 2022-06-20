package com.joaop.rickandmorty.usecase

import com.joaop.rickandmorty.data.callback.OnResultListener
import com.joaop.rickandmorty.data.entities.response.BaseResponsePagination
import com.joaop.rickandmorty.domain.entity.Character
import com.joaop.rickandmorty.data.repository.CharacterRepositoryImpl
import com.joaop.rickandmorty.data.repository.contract.CharacterRepository

class GetAllCharactersUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(
        page: Int,
        name: String? = null,
        listener: OnResultListener<BaseResponsePagination<List<Character>>?>
    ) {
        repository.getAllCharacters(page = page, name = name, listener = listener)
    }
}