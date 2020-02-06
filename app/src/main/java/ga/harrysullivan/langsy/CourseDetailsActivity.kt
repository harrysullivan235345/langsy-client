package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ga.harrysullivan.langsy.utils.CourseList
import ga.harrysullivan.langsy.utils.InjectorUtils
import ga.harrysullivan.langsy.utils.observeOnce
import ga.harrysullivan.langsy.view_models.ContentViewModel
import ga.harrysullivan.langsy.view_models.CurrentCourseViewModel
import kotlinx.android.synthetic.main.activity_course_details.*

class CourseDetailsActivity : AppCompatActivity() {

    private lateinit var mContentViewModel: ContentViewModel
    private lateinit var mCurrentCourseViewModel: CurrentCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)

        val currentCourseFactory = InjectorUtils.provideCurrentCourseViewModelFactory()
        mCurrentCourseViewModel = ViewModelProviders.of(this, currentCourseFactory)
            .get(CurrentCourseViewModel::class.java)

        mContentViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(ContentViewModel::class.java)

        init()
    }

    private fun init() {
        mCurrentCourseViewModel.getCurrentCourse().observe(this, Observer { currentCourse ->
            val course = currentCourse.course
            val languageFull = CourseList.localFromCode(course.language)

            course_details_language_label.text = languageFull

            mContentViewModel.fetchByLanguageAndStage(course.language, 500)
                .observeOnce(this, Observer { allContent ->
                    course_details_big_number.text = allContent.size.toString()
                })
        })

        setListeners()
    }

    private fun setListeners() {
        course_details_big_number.setOnClickListener {
            val intent =
                Intent(this@CourseDetailsActivity, LearnedWordsActivity::class.java)
            startActivity(intent)
        }

        course_details_back_button.setOnClickListener {
            val intent =
                Intent(this@CourseDetailsActivity, DashboardActivity::class.java)
            startActivity(intent)
        }

        course_details_drop_button.setOnClickListener {
            dropCourse()
        }
    }

    private fun dropCourse() {
        
    }
}
