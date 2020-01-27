package ga.harrysullivan.langsy.utils

import ga.harrysullivan.langsy.databases.TrainerDatabase
import ga.harrysullivan.langsy.repositories.TrainerRepository
import ga.harrysullivan.langsy.view_model_factories.TrainerViewModelFactory

object InjectorUtils {
    fun provideTrainerViewModelFactory(): TrainerViewModelFactory {
        val trainerRepository =
            TrainerRepository.getInstance(TrainerDatabase.getInstance().trainerDao)
        return TrainerViewModelFactory(trainerRepository)
    }
}