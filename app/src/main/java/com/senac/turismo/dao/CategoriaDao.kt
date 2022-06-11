package com.senac.turismo.dao

import androidx.room.*
import com.senac.turismo.model.Categoria
import com.senac.turismo.model.Pessoa
import com.senac.turismo.model.Viagem

@Dao
interface CategoriaDao {

    @Insert()
    suspend fun insert(categoria: Categoria)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(categoria: Categoria)

    @Delete
    suspend fun delete(categoria: Categoria)

    @Query("select * from Categoria")
    suspend fun findAll(): List<Categoria>

    @Query("select * from Categoria c where c.id = :id")
    suspend fun findById(id: Int): Categoria?

}