package com.senac.turismo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pessoa (
    val usuario: String?,
    val senha: String?,
    val nome: String,
    val email: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}