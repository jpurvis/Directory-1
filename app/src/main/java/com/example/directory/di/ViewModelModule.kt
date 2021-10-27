package com.example.directory.di

import com.example.directory.presentation.colleagues.ColleaguesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ColleaguesViewModel(get()) }
}