package com.senac.turismo.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senac.turismo.repository.ViagemRepository

class ViagemViewModelFactory(val app: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viagemRepository = ViagemRepository(app);
        val viewModel = ViagemViewModel(viagemRepository);
        return viewModel as T;
    }

}