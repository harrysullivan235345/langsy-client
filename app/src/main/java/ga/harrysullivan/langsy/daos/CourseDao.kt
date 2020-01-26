package ga.harrysullivan.langsy.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import ga.harrysullivan.langsy.models.Course

@Dao
public interface CourseDao {
    @Query("select * from Course")
    fun fetchAll(): LiveData<List<Course>>

    @Insert
    suspend fun insert(arg: Course)

    @Update
    suspend fun update(arg: Course)

    @Delete
    suspend fun delete(arg: Course)
}