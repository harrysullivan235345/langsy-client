package ga.harrysullivan.langsy.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ga.harrysullivan.langsy.databases.CourseDatabase
import ga.harrysullivan.langsy.models.Course
import ga.harrysullivan.langsy.repositories.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CourseRepository

    val allCourses: LiveData<List<Course>>

    init {
        val courseDao = CourseDatabase.getDatabase(application).courseDao()
        repository = CourseRepository(courseDao)
        allCourses = repository.allCourses
    }

    fun insert(arg: Course) = viewModelScope.launch {
        repository.insert(arg)
    }

    fun update(arg: Course) = viewModelScope.launch {
        repository.update(arg)
    }

    fun delete(arg: Course) = viewModelScope.launch {
        repository.delete(arg)
    }
}