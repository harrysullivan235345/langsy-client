package ga.harrysullivan.langsy.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ga.harrysullivan.langsy.models.Content
import ga.harrysullivan.langsy.stateData.TrainerStateData

class TrainerState: ViewModel() {

    val data: MutableLiveData<TrainerStateData> by lazy {
        MutableLiveData<TrainerStateData>()
    }

}