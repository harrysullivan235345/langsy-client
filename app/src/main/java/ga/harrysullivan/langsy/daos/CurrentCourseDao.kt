package ga.harrysullivan.langsy.daos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ga.harrysullivan.langsy.data.CurrentCourse

class CurrentCourseDao {

    private val currentCourse = MutableLiveData<CurrentCourse>()

    fun setCurrentCourse(course: CurrentCourse) {
        currentCourse.value = course
    }

    fun getCurrentCourse() = currentCourse as LiveData<CurrentCourse>
}