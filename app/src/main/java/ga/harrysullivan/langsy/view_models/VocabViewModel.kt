package ga.harrysullivan.langsy.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ga.harrysullivan.langsy.databases.ContentDatabase
import ga.harrysullivan.langsy.models.Content
import ga.harrysullivan.langsy.repositories.ContentRepository
import kotlinx.coroutines.launch

class VocabViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ContentRepository

    val allContent: LiveData<List<Content>>

    init {
        val vocabDao = ContentDatabase.getDatabase(application).vocabDao()
        repository = ContentRepository(vocabDao)
        allContent = repository.allContent
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