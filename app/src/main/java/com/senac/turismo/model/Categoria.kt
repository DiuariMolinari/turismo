package com.senac.turismo.model

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
data class Categoria (
    val nome: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}