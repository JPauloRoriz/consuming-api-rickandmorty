package com.joaop.rickandmorty.di

import com.joaop.rickandmorty.data.CharacterApi
import com.joaop.rickandmorty.data.repository.CharacterRepository
import com.joaop.rickandmorty.data.service.settings.retrofitConfig
import com.joaop.rickandmorty.usecase.GetAllCharactersUseCase
import com.joaop.rickandmorty.viewmodel.listCharacter.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    //ViewModel
    viewModel { CharacterViewModel(get()) }

    //Usecase
    factory { GetAllCharactersUseCase(get()) }


    //Repository
    factory { CharacterRepository(get()) }

    //Services
    single { retrofitConfig }
    single { get<Retrofit>().create(CharacterApi::class.java) }
}