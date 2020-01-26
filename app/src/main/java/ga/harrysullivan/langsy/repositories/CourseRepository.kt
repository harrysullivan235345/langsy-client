package ga.harrysullivan.langsy.repositories

import androidx.lifecycle.LiveData
import ga.harrysullivan.langsy.daos.CourseDao
import ga.harrysullivan.langsy.models.Course

class CourseRepository(private val courseDao: CourseDao) {
    val allCourses: LiveData<List<Course>> = courseDao.fetchAll()

    suspend fun insert(arg: Course) {
        courseDao.insert(arg)
    }

    suspend fun update(arg: Course) {
        courseDao.update(arg)
    }

    suspend fun delete(arg: Course) {
        courseDao.delete(arg)
    }
}