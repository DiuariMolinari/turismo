package com.senac.turismo.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senac.turismo.model.TipoViagem
import com.senac.turismo.model.Viagem
import com.senac.turismo.repository.ViagemRepository
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class ViagemViewModel(private val repository: ViagemRepository) : ViewModel() {

    var destino by mutableStateOf("")
    var tipo by mutableStateOf(TipoViagem.LAZER)
    var dataChegada by mutableStateOf(LocalDate.now())
    var dataPartida by mutableStateOf(LocalDate.now())
    var orcamento by mutableStateOf(0F)
    var pessoaId by mutableStateOf(-1)

    fun save() {
        val viagem = Viagem(destino, tipo, dataChegada, dataPartida, orcamento, pessoaId)
        viewModelScope.launch {
            repository.save(viagem)
        }
    }

    fun findAll(idPessoa: Int,onSuccess: (viagens: List<Viagem>) -> Unit){
        viewModelScope.launch {
            onSuccess(repository.findAll(idPessoa))
        }
    }
}
