package ga.harrysullivan.langsy.repositories

import ga.harrysullivan.langsy.daos.TrainerDao
import ga.harrysullivan.langsy.data.Trainer

class TrainerRepository private constructor(private val trainerDao: TrainerDao) {

    fun editTrainer(trainer: Trainer) {
        trainerDao.editTrainer(trainer)
    }

    fun getTrainer() = trainerDao.getTrainer()

    companion object {

        @Volatile
        private var instance: TrainerRepository? = null

        fun getInstance(trainerDao: TrainerDao) =
            instance ?: synchronized(this) {
                instance ?: TrainerRepository(trainerDao).also { instance = it }
            }
    }
}