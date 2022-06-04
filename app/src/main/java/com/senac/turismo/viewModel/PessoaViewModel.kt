package com.senac.turismo.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senac.turismo.model.Pessoa
import com.senac.turismo.repository.PessoaRepository
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*

class PessoaViewModel(private val repository: PessoaRepository) : ViewModel() {

    var usuario by mutableStateOf("")
    var senha by mutableStateOf("")
    var nome by mutableStateOf("")
    var email by mutableStateOf("")

    fun save() {
        val pessoa = Pessoa(usuario, senha, nome, email)
        viewModelScope.launch {
            repository.save(pessoa)
        }
    }

    fun login (onSuccess: () -> Unit, onFail: () -> Unit)  {
        viewModelScope.launch {
            val user = repository.findByUsername(usuario)
            if (user == null || !user.senha.equals(senha)) {
                onFail()
            }
            else {
                onSuccess()
            }
        }
    }

}
