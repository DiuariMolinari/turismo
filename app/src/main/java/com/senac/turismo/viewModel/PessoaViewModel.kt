package com.senac.turismo.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senac.turismo.model.Pessoa
import com.senac.turismo.repository.PessoaRepository
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
}
