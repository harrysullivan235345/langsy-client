package ga.harrysullivan.langsy.databases

import ga.harrysullivan.langsy.daos.TrainerDao

class TrainerDatabase private constructor() {

    var trainerDao = TrainerDao()
        private set

    companion object {

        @Volatile private var instance: TrainerDatabase? = null
        fun getInstance()=
            instance ?: synchronized(this) {
                instance ?: TrainerDatabase().also { instance = it }
            }
    }
}