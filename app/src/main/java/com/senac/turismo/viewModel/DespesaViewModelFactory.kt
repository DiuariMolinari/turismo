package com.senac.turismo.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senac.turismo.repository.CategoriaRepository
import com.senac.turismo.repository.DespesaRepository
import com.senac.turismo.repository.ViagemRepository

class DespesaViewModelFactory(val app: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val despesaRepository = DespesaRepository(app);
        val viewModel = DespesaViewModel(despesaRepository);
        return viewModel as T;
    }

}