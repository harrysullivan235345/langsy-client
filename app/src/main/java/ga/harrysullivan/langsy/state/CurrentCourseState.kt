package ga.harrysullivan.langsy.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ga.harrysullivan.langsy.models.Content
import ga.harrysullivan.langsy.models.Course
import ga.harrysullivan.langsy.stateData.CurrentCourseStateData

class CurrentCourseState: ViewModel() {

    val data: MutableLiveData<CurrentCourseStateData> by lazy {
        MutableLiveData<CurrentCourseStateData>()
    }

}