package ga.harrysullivan.langsy.view_model_factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ga.harrysullivan.langsy.repositories.TrainerRepository
import ga.harrysullivan.langsy.view_models.TrainerViewModel

class TrainerViewModelFactory(private val trainerRepository: TrainerRepository):
    ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrainerViewModel(trainerRepository) as T
    }
}