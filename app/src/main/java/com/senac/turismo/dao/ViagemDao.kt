package com.senac.turismo.dao

import androidx.room.*
import com.senac.turismo.model.Pessoa
import com.senac.turismo.model.Viagem

@Dao
interface ViagemDao {

    @Insert()
    suspend fun insert(viagem: Viagem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(viagem: Viagem)

    @Delete
    suspend fun delete(viagem: Viagem)

    @Query("select * from Viagem c where c.pessoaId = :idPessoa")
    suspend fun findAll(idPessoa :Int): List<Viagem>

    @Query("select * from Viagem c where c.id = :id")
    suspend fun findById(id: Int): Viagem?

}