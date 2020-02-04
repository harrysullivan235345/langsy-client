package ga.harrysullivan.langsy.repositories

import ga.harrysullivan.langsy.daos.CurrentCourseDao
import ga.harrysullivan.langsy.data.CurrentCourse

class CurrentCourseRepository private constructor(private val currentCourseDao: CurrentCourseDao) {

    fun setCurrentCourse(course: CurrentCourse) {
        currentCourseDao.setCurrentCourse(course)
    }

    fun getCurrentCourse() = currentCourseDao.getCurrentCourse()

    companion object {

        @Volatile
        private var instance: CurrentCourseRepository? = null

        fun getInstance(currentCourseDao: CurrentCourseDao) =
            instance ?: synchronized(this) {
                instance ?: CurrentCourseRepository(currentCourseDao).also { instance = it }
            }
    }
}