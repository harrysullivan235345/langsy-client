package ga.harrysullivan.langsy.databases

import ga.harrysullivan.langsy.daos.SpontaneousDao

class SpontaneousDatabase private constructor() {

    var spontaneousDao = SpontaneousDao()
        private set

    companion object {

        @Volatile
        private var instance: SpontaneousDatabase? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: SpontaneousDatabase().also { instance = it }
            }
    }
}