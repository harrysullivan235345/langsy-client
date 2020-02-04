package ga.harrysullivan.langsy.view_models

import androidx.lifecycle.ViewModel
import ga.harrysullivan.langsy.data.CurrentCourse
import ga.harrysullivan.langsy.repositories.CurrentCourseRepository

class CurrentCourseViewModel(private val currentCourseRepository: CurrentCourseRepository): ViewModel() {
    fun getCurrentCourse() = currentCourseRepository.getCurrentCourse()

    fun setCurrentCourse(currentCourse: CurrentCourse) {
        currentCourseRepository.setCurrentCourse(currentCourse)
    }
}