package com.joaop.rickandmorty.viewmodel.listCharacter

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.joaop.rickandmorty.R
import com.joaop.rickandmorty.data.callback.OnResultListener
import com.joaop.rickandmorty.data.entities.response.BaseResponsePagination
import com.joaop.rickandmorty.data.exceptions.WithoutInternetException
import com.joaop.rickandmorty.domain.entity.Character
import com.joaop.rickandmorty.usecase.GetAllCharactersUseCase
import com.joaop.rickandmorty.viewmodel.listCharacter.model.CharactersEvent
import com.joaop.rickandmorty.viewmodel.listCharacter.model.CharactersState
import com.joaop.rickandmorty.viewmodel.livedata.MultipleLiveState
import com.joaop.rickandmorty.viewmodel.livedata.SingleLiveEvent

class CharacterViewModel(
    private val getCharacterUseCase: GetAllCharactersUseCase,
    private val context: Context
) : ViewModel() {
    private var currentPage = 0
    private var lastPage: Int? = null
    private val listCharacters = mutableListOf<Character>()
    val stateLiveData = MultipleLiveState<CharactersState>()
    val eventLiveData = SingleLiveEvent<CharactersEvent>()

    init {
        refreshList()
    }

    fun refreshList(pagination: Boolean = false, name: String = "") {
        showLoading(pagination)
        if (pagination) {
            currentPage++
        } else {
            currentPage = 1
        }
        if (lastPage == currentPage) {

        } else {
            getCharacterUseCase(
                currentPage,
                name,
                object : OnResultListener<BaseResponsePagination<List<Character>>?> {
                    override fun onSuccess(result: BaseResponsePagination<List<Character>>?) {
                        lastPage = result?.info?.pages
                        result?.results?.let {
                            if (!pagination) {
                                listCharacters.clear()
                            }
                            listCharacters.addAll(it)
                            CharactersState.OnSuccessList(listCharacters).run()
                            hideLoading(pagination)
                        }
                    }

                    override fun onError(exception: Exception) {
                        currentPage--
                        if (exception is WithoutInternetException) {
                            CharactersEvent.ShowAlertMessage(
                                context.getString(R.string.message_error_internet)
                            ).run()
                        } else {
                            CharactersEvent.ShowAlertMessage(exception.message.toString()).run()
                        }
                        hideLoading(pagination)
                    }
                }
            )
        }
    }

    private fun showLoading(pagination: Boolean) {
        if (pagination) {
            CharactersState.ShowLoadingPagination(true).run()
        } else {
            CharactersState.ShowLoadingScreen(true).run()
        }
    }

    private fun hideLoading(pagination: Boolean) {
        if (pagination) {
            CharactersState.ShowLoadingPagination(false).run()
        } else {
            CharactersState.ShowLoadingScreen(false).run()
        }
    }

    private fun CharactersEvent.run() {
        eventLiveData.value = this
    }

    private fun CharactersState.run() {
        stateLiveData.setValue(this)
    }


}