package ga.harrysullivan.langsy.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import ga.harrysullivan.langsy.constants.GrammarPartOfSpeech
import ga.harrysullivan.langsy.models.Content

@Dao
public interface ContentDao {
    @Query("select * from Content")
    fun fetchAll(): LiveData<List<Content>>

    @Query("select * from Content where language = :langCode and stage < :stage")
    fun fetchByLanguageAndStage(langCode: String, stage: Int): LiveData<List<Content>>

    @Query("select * from Content where partOfSpeech = '${GrammarPartOfSpeech.PREREQS}' and language= :langCode and line = :line")
    fun fetchPrereq(langCode: String, line: Int): LiveData<List<Content>>

    @Insert
    suspend fun insert(arg: Content)

    @Update
    suspend fun update(arg: Content)

    @Delete
    suspend fun delete(arg: Content)
}