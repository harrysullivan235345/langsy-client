package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ga.harrysullivan.langsy.adapters.LearnedWordsAdapter
import ga.harrysullivan.langsy.utils.CourseList
import ga.harrysullivan.langsy.utils.InjectorUtils
import ga.harrysullivan.langsy.utils.observeOnce
import ga.harrysullivan.langsy.view_models.ContentViewModel
import ga.harrysullivan.langsy.view_models.CurrentCourseViewModel
import kotlinx.android.synthetic.main.activity_learned_words.*

class LearnedWordsActivity : AppCompatActivity() {

    private lateinit var mContentViewModel: ContentViewModel
    private lateinit var mCurrentCourseViewModel: CurrentCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learned_words)

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
            mContentViewModel.fetchByLanguageAndStage(course.language, 500)
                .observeOnce(this, Observer { allContent ->
                    LearnedWordsAdapter(
                        this.layoutInflater,
                        learned_words_list,
                        allContent,
                        application
                    )
                })

            learned_words_language_label.text = CourseList.localFromCode(course.language)
        })

        setListeners()
    }

    private fun setListeners() {
        learned_words_back_button.setOnClickListener {
            val intent =
                Intent(this@LearnedWordsActivity, CourseDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}
