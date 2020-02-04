package ga.harrysullivan.langsy.view_model_factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ga.harrysullivan.langsy.repositories.SpontaneousRepository
import ga.harrysullivan.langsy.view_models.SpontaneousViewModel

class SpontaneousViewModelFactory(private val spontaneousRepository: SpontaneousRepository):
    ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SpontaneousViewModel(spontaneousRepository) as T
    }
}