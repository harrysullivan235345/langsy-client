package ga.harrysullivan.langsy.daos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ga.harrysullivan.langsy.data.CurrentCourse
import ga.harrysullivan.langsy.data.Spontaneous
import ga.harrysullivan.langsy.data.Trainer
import ga.harrysullivan.langsy.models.Course

class SpontaneousDao {

    private val spontaneous = MutableLiveData<Spontaneous>()

    fun setSpontaneous(sp: Spontaneous) {
        spontaneous.value = sp
    }

    fun getSpontaneous() = spontaneous as LiveData<Spontaneous>
}