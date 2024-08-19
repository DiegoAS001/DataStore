package edu.ifsp.dmo.datastoreexemplo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.ifsp.dmo.datastoreexemplo.data.repository.UserRepository

class MainViewModelFactory(
    private val userRepository: UserRepository

): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(userRepository) as T
        }
        throw IllegalArgumentException("View Model deconhecido")
    }
}