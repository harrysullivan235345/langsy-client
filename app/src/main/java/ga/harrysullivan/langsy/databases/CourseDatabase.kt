package ga.harrysullivan.langsy.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ga.harrysullivan.langsy.daos.CourseDao
import ga.harrysullivan.langsy.models.Course

@Database(entities = [Course::class], version = 1)
abstract class CourseDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao

    companion object {

        @Volatile
        private var INSTANCE: CourseDatabase? = null

        fun getDatabase(context: Context): CourseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CourseDatabase::class.java,
                    "CourseDatabase"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }

}