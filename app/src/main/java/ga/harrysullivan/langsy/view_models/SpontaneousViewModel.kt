package ga.harrysullivan.langsy.view_models

import androidx.lifecycle.ViewModel
import ga.harrysullivan.langsy.data.CurrentCourse
import ga.harrysullivan.langsy.data.Spontaneous
import ga.harrysullivan.langsy.data.Trainer
import ga.harrysullivan.langsy.models.Course
import ga.harrysullivan.langsy.repositories.CurrentCourseRepository
import ga.harrysullivan.langsy.repositories.SpontaneousRepository
import ga.harrysullivan.langsy.repositories.TrainerRepository

class SpontaneousViewModel(private val spontaneousRepository: SpontaneousRepository): ViewModel() {
    fun getSpontaneous() = spontaneousRepository.getSpontaneous()

    fun setSpontaneous(spontaneous: Spontaneous) {
        spontaneousRepository.setSpontaneous(spontaneous)
    }
}