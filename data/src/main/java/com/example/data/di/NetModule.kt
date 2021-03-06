package com.example.data.di

import com.example.data.service.UserApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

val NET_MODULE = module {

    single<UserApiService> { get<Retrofit>().create(UserApiService::class.java) }

    single<Gson> { GsonBuilder().setLenient().create() }

    single { HttpLoggingInterceptor().setLevel(Level.BODY) }

    single { OkHttpClient.Builder().addInterceptor(get<HttpLoggingInterceptor>()).build() }

    single<GsonConverterFactory> { GsonConverterFactory.create(get<Gson>()) }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get<OkHttpClient>())
            .build()
    }
}