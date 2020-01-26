package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ga.harrysullivan.langsy.adapters.CourseSelectionAdapter
import ga.harrysullivan.langsy.models.Course
import ga.harrysullivan.langsy.utils.CourseList
import ga.harrysullivan.langsy.view_models.CourseViewModel
import kotlinx.android.synthetic.main.activity_course_selection.*

class CourseSelectionActivity : AppCompatActivity() {

    private lateinit var courseViewModel: CourseViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_selection)

        courseViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(CourseViewModel::class.java)

        CourseSelectionAdapter(
            this.layoutInflater,
            course_selection_list,
            CourseList.languages,
            fun(langCode: String) {

                val created = Course(0, langCode, 0.0)

                courseViewModel.insert(created)

                val intent = Intent(this@CourseSelectionActivity, DashboardActivity::class.java)
                startActivity(intent)

            })
    }
}
