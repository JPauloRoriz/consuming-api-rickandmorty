package com.joaop.rickandmorty.viewmodel.listCharacter

import android.content.Context
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
    private val getAllCharacterUseCase: GetAllCharactersUseCase,
    private val context: Context
) : ViewModel() {
    private var currentPage = 0
    private var queryFilter = ""
    private var lastPage: Int = 2
    private val listCharacters = mutableListOf<Character>()
    val stateLiveData = MultipleLiveState<CharactersState>()
    val eventLiveData = SingleLiveEvent<CharactersEvent>()

    init {
        refreshList(true)
    }

    fun refreshList(resetList: Boolean = false) {
        if (resetList) {
            listCharacters.clear()
            this.queryFilter = ""
            currentPage = 1
        } else {
            currentPage++
        }
        if (currentPage >= lastPage) {

        } else {
            showLoading(resetList)
            getAllCharacterUseCase(
                page = currentPage,
                name = queryFilter,
                listener = object : OnResultListener<BaseResponsePagination<List<Character>>?> {
                    override fun onSuccess(result: BaseResponsePagination<List<Character>>?) {
                        result?.info?.pages?.let { lastPage = it }
                        result?.results?.let {
                            listCharacters.addAll(it)
                            CharactersState.OnSuccessList(listCharacters).run()
                            hideLoading(resetList)
                            if (resetList) CharactersEvent.ScrollToTop.run()
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
                        hideLoading(resetList)
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

    fun getCharacterByQuery(query: String) {
        this.queryFilter = query
        currentPage = 0
        listCharacters.clear()
        refreshList(resetList = false)

    }


}