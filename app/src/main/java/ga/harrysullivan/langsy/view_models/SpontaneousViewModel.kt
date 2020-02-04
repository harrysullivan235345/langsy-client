package ga.harrysullivan.langsy.view_models

import androidx.lifecycle.ViewModel
import ga.harrysullivan.langsy.data.Spontaneous
import ga.harrysullivan.langsy.repositories.SpontaneousRepository

class SpontaneousViewModel(private val spontaneousRepository: SpontaneousRepository): ViewModel() {
    fun getSpontaneous() = spontaneousRepository.getSpontaneous()

    fun setSpontaneous(spontaneous: Spontaneous) {
        spontaneousRepository.setSpontaneous(spontaneous)
    }
}