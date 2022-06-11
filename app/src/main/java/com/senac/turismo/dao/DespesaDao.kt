package com.senac.turismo.dao

import androidx.room.*
import com.senac.turismo.model.Despesa
import com.senac.turismo.model.Pessoa
import com.senac.turismo.model.Viagem

@Dao
interface DespesaDao {

    @Insert()
    suspend fun insert(despesa: Despesa)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(despesa: Despesa)

    @Delete
    suspend fun delete(despesa: Despesa)

    @Query("select * from Despesa")
    suspend fun findAll(): List<Despesa>

    @Query("select * from Despesa c where c.id = :id")
    suspend fun findById(id: Int): Despesa?

}