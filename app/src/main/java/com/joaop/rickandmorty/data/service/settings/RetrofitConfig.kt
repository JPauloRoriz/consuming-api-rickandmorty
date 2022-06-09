package com.joaop.rickandmorty.data.service.settings

import com.joaop.rickandmorty.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val retrofitConfig: Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }).build())
    .addConverterFactory(GsonConverterFactory.create())
    .build()