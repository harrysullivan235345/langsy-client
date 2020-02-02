package ga.harrysullivan.langsy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ga.harrysullivan.langsy.adapters.LearnedWordsAdapter
import ga.harrysullivan.langsy.utils.InjectorUtils
import ga.harrysullivan.langsy.utils.observeOnce
import ga.harrysullivan.langsy.view_models.ContentViewModel
import ga.harrysullivan.langsy.view_models.CurrentCourseViewModel
import kotlinx.android.synthetic.main.activity_course_details.*
import kotlinx.android.synthetic.main.activity_learned_words.*

class CourseDetailsActivity : AppCompatActivity() {

    private lateinit var mContentViewModel: ContentViewModel
    private lateinit var mCurrentCourseViewModel: CurrentCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)

        val currentCourseFactory = InjectorUtils.provideCurrentCourseViewModelFactory()
        mCurrentCourseViewModel = ViewModelProviders.of(this, currentCourseFactory)
            .get(CurrentCourseViewModel::class.java)

        mContentViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ContentViewModel::class.java)

        init()
    }

    private fun init() {
        mCurrentCourseViewModel.getCurrentCourse().observe(this, Observer { currentCourse ->
            val course = currentCourse.course
            mContentViewModel.fetchByLanguageAndStage(course.language, 500).observeOnce(this, Observer { allContent ->
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
    }
}
