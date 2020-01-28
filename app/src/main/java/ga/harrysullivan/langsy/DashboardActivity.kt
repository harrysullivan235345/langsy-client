package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ga.harrysullivan.langsy.adapters.CourseListAdapter
import ga.harrysullivan.langsy.constants.ContentType
import ga.harrysullivan.langsy.constants.SpacedRepetition
import ga.harrysullivan.langsy.models.Content
import ga.harrysullivan.langsy.models.Course
import ga.harrysullivan.langsy.utils.Corpora
import ga.harrysullivan.langsy.utils.InjectorUtils
import ga.harrysullivan.langsy.utils.observeOnce
import ga.harrysullivan.langsy.view_models.ContentViewModel
import ga.harrysullivan.langsy.view_models.CourseViewModel
import ga.harrysullivan.langsy.view_models.TrainerViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_main.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var courseViewModel: CourseViewModel
    private lateinit var contentViewModel: ContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dasboard_add_course_button.setOnClickListener {
            val intent = Intent(this@DashboardActivity, CourseSelectionActivity::class.java)
            startActivity(intent)
        }

        courseViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(CourseViewModel::class.java)
        contentViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ContentViewModel::class.java)

        courseViewModel.allCourses.observeOnce(this, Observer { courses ->
            CourseListAdapter(this.layoutInflater, dashboard_courselist, courses, ::courseSelectCallback)
        })
    }

    private fun courseSelectCallback(course: Course) {
        Log.d("WTF IS THIS", "Getting called again")
        contentViewModel.fetchByLanguageAndStage(course.language, SpacedRepetition.THRESHOLD_OF_PROBABALISTIC_MASTERY).observeOnce(this, Observer {
            if(it.size > SpacedRepetition.WORKING_MEMORY_CAPACITY) {

                practice()

            } else {

                newContent(it, course)

            }
        })
    }

    private fun newContent(
        selectedContent: List<Content>,
        course: Course
    ) {
        val totalGrammar = selectedContent.takeIf { it.isNotEmpty() }?.fold(0) { acc, content ->
            if (content.type == ContentType.GRAMMAR) acc + 1 else acc
        } ?: 0

        val corpora = Corpora(this.application)

        val trainerFactory = InjectorUtils.provideTrainerViewModelFactory()
        val viewModel = ViewModelProviders.of(this, trainerFactory)
            .get(TrainerViewModel::class.java)

        if (totalGrammar > SpacedRepetition.MAX_GRAMMAR) {
            val content = corpora.getVocab(course.language)
            val trainer = corpora.getTrainer(content)

            contentViewModel.insert(content)
            viewModel.editTrainer(trainer)
        } else {
            val content = corpora.getGrammar(course.language, selectedContent)
            val trainer = corpora.getTrainer(content)

            contentViewModel.insert(content)
            viewModel.editTrainer(trainer)
        }

        val intent = Intent(this@DashboardActivity, VisualLearningActivity::class.java)
        startActivity(intent)
    }

    private fun practice() {
        val intent = Intent(this@DashboardActivity, SemanticLearningActivity::class.java)
        startActivity(intent)
    }
}
