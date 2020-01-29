package ga.harrysullivan.langsy.view_model_factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ga.harrysullivan.langsy.repositories.CurrentCourseRepository
import ga.harrysullivan.langsy.repositories.TrainerRepository
import ga.harrysullivan.langsy.view_models.CurrentCourseViewModel
import ga.harrysullivan.langsy.view_models.TrainerViewModel

class CurrentCourseViewModelFactory(private val currentCourseRepository: CurrentCourseRepository):
    ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentCourseViewModel(currentCourseRepository) as T
    }
}