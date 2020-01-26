package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ga.harrysullivan.langsy.adapters.CourseListAdapter
import ga.harrysullivan.langsy.view_models.CourseViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_main.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var courseViewModel: CourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dasboard_add_course_button.setOnClickListener {
            val intent = Intent(this@DashboardActivity, CourseSelectionActivity::class.java)
            startActivity(intent)
        }

        courseViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(CourseViewModel::class.java)
        courseViewModel.allCourses.observe(this, Observer { courses ->
            CourseListAdapter(this.layoutInflater, dashboard_courselist, courses)
        })
    }
}
