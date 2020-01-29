package ga.harrysullivan.langsy.daos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ga.harrysullivan.langsy.data.CurrentCourse
import ga.harrysullivan.langsy.data.Trainer
import ga.harrysullivan.langsy.models.Course

class CurrentCourseDao {

    private val currentCourse = MutableLiveData<CurrentCourse>()

    fun setCurrentCourse(course: CurrentCourse) {
        currentCourse.value = course
    }

    fun getCurrentCourse() = currentCourse as LiveData<CurrentCourse>
}