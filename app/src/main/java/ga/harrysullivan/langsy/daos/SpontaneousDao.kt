package ga.harrysullivan.langsy.daos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ga.harrysullivan.langsy.data.Spontaneous

class SpontaneousDao {

    private val spontaneous = MutableLiveData<Spontaneous>()

    fun setSpontaneous(sp: Spontaneous) {
        spontaneous.value = sp
    }

    fun getSpontaneous() = spontaneous as LiveData<Spontaneous>
}