package ga.harrysullivan.langsy.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ga.harrysullivan.langsy.models.Content
import ga.harrysullivan.langsy.models.Course

class CurrentCourseState: ViewModel() {

    val course: MutableLiveData<Course> by lazy {
        MutableLiveData<Course>()
    }

}