package ga.harrysullivan.langsy.view_models

import androidx.lifecycle.ViewModel
import ga.harrysullivan.langsy.data.Trainer
import ga.harrysullivan.langsy.repositories.TrainerRepository

class TrainerViewModel(private val trainerRepository: TrainerRepository): ViewModel() {
    fun getTrainer() = trainerRepository.getTrainer()

    fun editTrainer(trainer: Trainer) {
        trainerRepository.editTrainer(trainer)
    }
}