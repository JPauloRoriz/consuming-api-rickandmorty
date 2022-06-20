package com.joaop.rickandmorty.data.repository.contract

import com.joaop.rickandmorty.data.callback.OnResultListener
import com.joaop.rickandmorty.data.entities.response.BaseResponsePagination
import com.joaop.rickandmorty.domain.entity.Character

interface CharacterRepository {
    fun getAllCharacters(
        page : Int = 1,
        name : String? = null,
        listener: OnResultListener<BaseResponsePagination<List<Character>>?>
    )
}