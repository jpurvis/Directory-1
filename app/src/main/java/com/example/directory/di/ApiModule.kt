package com.example.directory.di

import com.example.directory.BuildConfig
import com.example.directory.framework.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

var client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

val apiModule = module {
    fun provideApi() : ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiService::class.java)
    }

    single { provideApi() }
}