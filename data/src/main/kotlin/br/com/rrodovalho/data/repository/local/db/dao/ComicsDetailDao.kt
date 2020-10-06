package br.com.rrodovalho.data.repository.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.rrodovalho.data.repository.local.db.entities.ComicsDetailEntity

@Dao
interface ComicsDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveComicsDetail(comicsDetailEntity: ComicsDetailEntity)

    @Query("SELECT * FROM ComicsDetail WHERE id = :id")
    fun getComicsDetailById(id: String): ComicsDetailEntity
}