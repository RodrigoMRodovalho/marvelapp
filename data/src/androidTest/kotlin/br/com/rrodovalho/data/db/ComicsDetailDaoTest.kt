package br.com.rrodovalho.data.db

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import br.com.rrodovalho.data.repository.local.db.LocalDatabase
import br.com.rrodovalho.data.repository.local.db.dao.ComicsDetailDao
import br.com.rrodovalho.data.repository.local.db.entities.ComicsDetailEntity
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComicsDetailDaoTest {

    private lateinit var database: LocalDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context, LocalDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun comicsDetailDao_WhenCachingComicsDetail_ShouldSaveAndQueryWithSuccess() {

        val comicsDetailEntity = ComicsDetailEntity("id", "name",
            "resourceUri", "description",
            "imageUrl")

        database.comicsDetailDao().saveComicsDetail(comicsDetailEntity)

        val comicsDetail = database.comicsDetailDao().getComicsDetailById("id")

        assertNotNull(comicsDetail)
        comicsDetail?.let {
            assertEquals(comicsDetailEntity.id, comicsDetail.id)
            assertEquals(comicsDetailEntity.name, comicsDetail.name)
            assertEquals(comicsDetailEntity.description, comicsDetail.description)
            assertEquals(comicsDetailEntity.resourceUri, comicsDetail.resourceUri)
            assertEquals(comicsDetailEntity.imageUrl, comicsDetail.imageUrl)
        }

    }

    @Test
    fun comicsDetailDao_WhenSearchingComicsDetailNotSaved_ShouldReturnError() {
        assertNull(database.comicsDetailDao().getComicsDetailById("identifier"))
    }

}