package com.senac.turismo.repository

import android.app.Application
import com.senac.turismo.dao.*
import com.senac.turismo.model.Categoria
import com.senac.turismo.model.Despesa
import com.senac.turismo.model.Pessoa
import com.senac.turismo.model.Viagem

class DespesaRepository(app: Application) {

    private val despesaDao: DespesaDao

    init {
        despesaDao = AppDatabaseConnection
            .getDB(app).despesaDao()
    }

    suspend fun save(despesa: Despesa) {
        if (despesa.id == 0) {
            despesaDao.insert(despesa)
        }
        else {
            despesaDao.update(despesa)
        }
    }

    suspend fun findAll(): List<Despesa> = despesaDao.findAll()

    suspend fun findById(id: Int) = despesaDao.findById(id)

    suspend fun delete(despesa: Despesa) = despesaDao.delete(despesa)



}