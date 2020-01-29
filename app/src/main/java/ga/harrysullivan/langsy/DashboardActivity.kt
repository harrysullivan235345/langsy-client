package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ga.harrysullivan.langsy.adapters.CourseListAdapter
import ga.harrysullivan.langsy.constants.ContentType
import ga.harrysullivan.langsy.constants.SpacedRepetition
import ga.harrysullivan.langsy.controllers.Engine
import ga.harrysullivan.langsy.data.CurrentCourse
import ga.harrysullivan.langsy.models.Content
import ga.harrysullivan.langsy.models.Course
import ga.harrysullivan.langsy.utils.Corpora
import ga.harrysullivan.langsy.utils.InjectorUtils
import ga.harrysullivan.langsy.utils.observeOnce
import ga.harrysullivan.langsy.view_models.ContentViewModel
import ga.harrysullivan.langsy.view_models.CourseViewModel
import ga.harrysullivan.langsy.view_models.CurrentCourseViewModel
import ga.harrysullivan.langsy.view_models.TrainerViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var mCourseViewModel: CourseViewModel
    private lateinit var mContentViewModel: ContentViewModel
    private lateinit var mTrainerViewModel: TrainerViewModel
    private lateinit var mCurrentCourseViewModel: CurrentCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dasboard_add_course_button.setOnClickListener {
            val intent = Intent(this@DashboardActivity, CourseSelectionActivity::class.java)
            startActivity(intent)
        }

        val trainerFactory = InjectorUtils.provideTrainerViewModelFactory()
        mTrainerViewModel = ViewModelProviders.of(this, trainerFactory)
            .get(TrainerViewModel::class.java)

        val currentCourseFactory = InjectorUtils.provideCurrentCourseViewModelFactory()
        mCurrentCourseViewModel = ViewModelProviders.of(this, currentCourseFactory)
            .get(CurrentCourseViewModel::class.java)

        mCourseViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(CourseViewModel::class.java)
        mContentViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ContentViewModel::class.java)

        mCourseViewModel.allCourses.observeOnce(this, Observer { courses ->
            CourseListAdapter(this.layoutInflater, dashboard_courselist, courses, ::courseSelectCallback)
        })
    }

    private fun courseSelectCallback(course: Course) {
        mCurrentCourseViewModel.setCurrentCourse(
            CurrentCourse(course)
        )
        mContentViewModel.fetchByLanguageAndStage(course.language, SpacedRepetition.THRESHOLD_OF_PROBABALISTIC_MASTERY).observeOnce(this, Observer {
            val shouldGetNew = Engine.shouldDoNew(it)
            if(shouldGetNew) {

                practice(it)

            } else {

                newContent(it, course)

            }
        })
    }

    private fun newContent(
        selectedContent: List<Content>,
        course: Course
    ) {
        val (content, trainer) = Engine.newContent(selectedContent, this.application, course)
        mContentViewModel.insert(content)
        mTrainerViewModel.editTrainer(trainer)

        val intent = Intent(this@DashboardActivity, VisualLearningActivity::class.java)
        startActivity(intent)
    }

    private fun practice(selectedContent: List<Content>) {
        val trainer = Engine.practice(selectedContent, this.application)
        mTrainerViewModel.editTrainer(trainer)

        val intent = Intent(this@DashboardActivity, AssessmentActivity::class.java)
        startActivity(intent)
    }

}
