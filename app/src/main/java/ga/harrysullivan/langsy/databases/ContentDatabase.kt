package ga.harrysullivan.langsy.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ga.harrysullivan.langsy.daos.ContentDao
import ga.harrysullivan.langsy.models.Course

@Database(entities = [Course::class], version = 1)
abstract class ContentDatabase: RoomDatabase() {

    abstract fun vocabDao(): ContentDao

    companion object {

        @Volatile
        private var INSTANCE: ContentDatabase? = null

        fun getDatabase(context: Context): ContentDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContentDatabase::class.java,
                    "VocabDatabase"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }

}