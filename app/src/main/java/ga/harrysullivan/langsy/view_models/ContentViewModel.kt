package ga.harrysullivan.langsy.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ga.harrysullivan.langsy.databases.ContentDatabase
import ga.harrysullivan.langsy.models.Content
import ga.harrysullivan.langsy.repositories.ContentRepository
import kotlinx.coroutines.launch

class ContentViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ContentRepository

    val allContent: LiveData<List<Content>>

    init {
        val contentDao = ContentDatabase.getDatabase(application).contentDao()
        repository = ContentRepository(contentDao)
        allContent = repository.allContent
    }

    fun fetchByLanguageAndStage(langCode: String, stage: Int): LiveData<List<Content>> {
        return repository.fetchByLanguageAndStage(langCode, stage)
    }

    fun fetchPrereq(langCode: String, line: Int): LiveData<List<Content>> {
        return repository.fetchPrereq(langCode, line)
    }

    fun insert(arg: Content) = viewModelScope.launch {
        repository.insert(arg)
    }

    fun update(arg: Content) = viewModelScope.launch {
        repository.update(arg)
    }

    fun delete(arg: Content) = viewModelScope.launch {
        repository.delete(arg)
    }
}