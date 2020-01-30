package ga.harrysullivan.langsy.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ga.harrysullivan.langsy.models.Content

class TrainerState: ViewModel() {

    val content: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val translation: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val contentRef: MutableLiveData<Content> by lazy {
        MutableLiveData<Content>()
    }

}