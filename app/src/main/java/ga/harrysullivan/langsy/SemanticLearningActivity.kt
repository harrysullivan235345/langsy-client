package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ga.harrysullivan.langsy.adapters.RevealPanelAdapter
import ga.harrysullivan.langsy.constants.SpacedRepetition
import ga.harrysullivan.langsy.controllers.Engine
import ga.harrysullivan.langsy.models.Content
import ga.harrysullivan.langsy.utils.InjectorUtils
import ga.harrysullivan.langsy.utils.observeOnce
import ga.harrysullivan.langsy.view_models.ContentViewModel
import ga.harrysullivan.langsy.view_models.TrainerViewModel
import kotlinx.android.synthetic.main.activity_semantic_learning.*
import net.gcardone.junidecode.Junidecode.unidecode

class SemanticLearningActivity : AppCompatActivity() {

    private lateinit var mContentViewModel: ContentViewModel
    private lateinit var mTrainerViewModel: TrainerViewModel
    private lateinit var revealPanelAdapter: RevealPanelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semantic_learning)

        val trainerFactory = InjectorUtils.provideTrainerViewModelFactory()
        mTrainerViewModel = ViewModelProviders.of(this, trainerFactory)
            .get(TrainerViewModel::class.java)

        mContentViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(
            ContentViewModel::class.java)

        revealPanelAdapter = RevealPanelAdapter(this.layoutInflater, semantic_learning_root)

        init()
    }

    private fun init() {
        mTrainerViewModel.getTrainer().observeOnce(this, Observer {trainer ->
            semantic_learning_content.text = trainer.content
            revealPanelAdapter.setContent(trainer.translation, unidecode(trainer.translation))

            mContentViewModel.fetchByLanguageAndStage(trainer.contentObj.language, SpacedRepetition.THRESHOLD_OF_PROBABALISTIC_MASTERY).observeOnce(this, Observer { selectedContent ->
                prepareLoadAssessment(selectedContent)
            })
        })

        setListeners()
    }

    private fun setListeners() {
        semantic_learning_reveal.setOnClickListener {
            revealPanelAdapter.show()
        }

        semantic_learning_home_button.setOnClickListener {
            val intent =
                Intent(this@SemanticLearningActivity, DashboardActivity::class.java)
            startActivity(intent)
        }
    }

    private fun prepareLoadAssessment(selectedContent: List<Content>) {
        semantic_learning_next_button.setOnClickListener {

            val trainer = Engine.practice(selectedContent, this.application)

            mTrainerViewModel.editTrainer(trainer)
            val intent =
                Intent(this@SemanticLearningActivity, AssessmentActivity::class.java)
            startActivity(intent)
        }
    }
}
