package ga.harrysullivan.langsy.repositories

import ga.harrysullivan.langsy.daos.CurrentCourseDao
import ga.harrysullivan.langsy.daos.SpontaneousDao
import ga.harrysullivan.langsy.data.Spontaneous

class SpontaneousRepository private constructor(private val spontaneousDao: SpontaneousDao) {

    fun setSpontaneous(spontaneous: Spontaneous) {
        spontaneousDao.setSpontaneous(spontaneous)
    }

    fun getSpontaneous() = spontaneousDao.getSpontaneous()

    companion object {

        @Volatile
        private var instance: SpontaneousRepository? = null

        fun getInstance(spontaneousDao: SpontaneousDao) =
            instance ?: synchronized(this) {
                instance ?: SpontaneousRepository(spontaneousDao).also { instance = it }
            }
    }
}