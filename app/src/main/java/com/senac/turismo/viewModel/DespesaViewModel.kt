package com.senac.turismo.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senac.turismo.model.Categoria
import com.senac.turismo.model.Despesa
import com.senac.turismo.model.TipoViagem
import com.senac.turismo.model.Viagem
import com.senac.turismo.repository.DespesaRepository
import com.senac.turismo.repository.ViagemRepository
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class DespesaViewModel(private val repository: DespesaRepository) : ViewModel() {

    var data by mutableStateOf(LocalDate.now())
    var valor by mutableStateOf(0.0)
    var descricao by mutableStateOf("")
    var local by mutableStateOf("")
    var categoriaId by mutableStateOf(-1)
    var viagemId by mutableStateOf(-1)

    fun save() {
        val despesa = Despesa(data, valor, descricao, local, categoriaId, viagemId)
        viewModelScope.launch {
            repository.save(despesa)
        }
    }
}
