package com.senac.turismo.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senac.turismo.repository.PessoaRepository

class PessoaViewModelFactory(val app: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val pessoaRepository = PessoaRepository(app);
        val viewModel = PessoaViewModel(pessoaRepository);
        return viewModel as T;
    }

}