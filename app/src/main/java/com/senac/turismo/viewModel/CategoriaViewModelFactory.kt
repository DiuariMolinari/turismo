package com.senac.turismo.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senac.turismo.repository.CategoriaRepository
import com.senac.turismo.repository.ViagemRepository

class CategoriaViewModelFactory(val app: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val categoriaRepository = CategoriaRepository(app);
        val viewModel = CategoriaViewModel(categoriaRepository);
        return viewModel as T;
    }

}