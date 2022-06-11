package com.senac.turismo.repository

import android.app.Application
import com.senac.turismo.dao.AppDatabaseConnection
import com.senac.turismo.dao.CategoriaDao
import com.senac.turismo.dao.PessoaDao
import com.senac.turismo.dao.ViagemDao
import com.senac.turismo.model.Categoria
import com.senac.turismo.model.Pessoa
import com.senac.turismo.model.Viagem

class CategoriaRepository(app: Application) {

    private val categoriaDao: CategoriaDao

    init {
        categoriaDao = AppDatabaseConnection
            .getDB(app).categoriaDao()
    }

    suspend fun save(categoria: Categoria) {
        if (categoria.id == 0) {
            categoriaDao.insert(categoria)
        }
        else {
            categoriaDao.update(categoria)
        }
    }

    suspend fun findAll(): List<Categoria> = categoriaDao.findAll()

    suspend fun findById(id: Int) = categoriaDao.findById(id)

    suspend fun delete(categoria: Categoria) = categoriaDao.delete(categoria)



}