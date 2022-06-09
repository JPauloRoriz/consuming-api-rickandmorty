package com.joaop.rickandmorty.viewmodel.listCharacter

import androidx.lifecycle.ViewModel
import com.joaop.rickandmorty.data.callback.OnResultListener
import com.joaop.rickandmorty.data.entities.response.BaseResponse
import com.joaop.rickandmorty.domain.entity.Character
import com.joaop.rickandmorty.usecase.GetAllCharactersUseCase
import com.joaop.rickandmorty.viewmodel.listCharacter.model.CharactersEvent
import com.joaop.rickandmorty.viewmodel.listCharacter.model.CharactersState
import com.joaop.rickandmorty.viewmodel.livedata.MultipleLiveState
import com.joaop.rickandmorty.viewmodel.livedata.SingleLiveEvent

class CharacterViewModel(
    private val getCharacterUseCase: GetAllCharactersUseCase
) : ViewModel() {
    private var currentPage = 0
    private val listCharacters = mutableListOf<Character>()
    val stateLiveData = MultipleLiveState<CharactersState>()
    val eventLiveData = SingleLiveEvent<CharactersEvent>()

    init {
        refreshList()
    }

    fun refreshList(pagination: Boolean = false) {
        showLoading(pagination)
        if (pagination) {
            currentPage++
        } else {
            currentPage = 1
        }
        getCharacterUseCase(
            currentPage,
            object : OnResultListener<BaseResponse<List<Character>>?> {
                override fun onSuccess(result: BaseResponse<List<Character>>?) {
                    result?.results?.let {
                        if (!pagination) {
                            listCharacters.clear()
                        }
                        listCharacters.addAll(it)
                        stateLiveData.setValue(CharactersState.OnSuccessList(listCharacters))
                        hideLoading(pagination)
                    }
                }

                override fun onError(exception: Exception) {
                    currentPage--
                    hideLoading(pagination)
                }
            }
        )
    }

    private fun showLoading(pagination: Boolean) {
        if (pagination) {
            stateLiveData.setValue(CharactersState.ShowLoadingPagination(true))
        } else {
            stateLiveData.setValue(CharactersState.ShowLoading)
        }
    }

    private fun hideLoading(pagination: Boolean) {
        if (pagination) {
            stateLiveData.setValue(CharactersState.ShowLoadingPagination(false))
        } else {
            stateLiveData.setValue(CharactersState.HideLoading)
        }
    }

}