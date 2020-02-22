package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ga.harrysullivan.langsy.adapters.RevealPanelAdapter
import ga.harrysullivan.langsy.data.Trainer
import ga.harrysullivan.langsy.utils.InjectorUtils
import ga.harrysullivan.langsy.utils.Validation
import ga.harrysullivan.langsy.utils.observeOnce
import ga.harrysullivan.langsy.view_models.ContentViewModel
import ga.harrysullivan.langsy.view_models.TrainerViewModel
import kotlinx.android.synthetic.main.activity_application_learning.*
import net.gcardone.junidecode.Junidecode.unidecode

class ApplicationLearningActivity : AppCompatActivity() {

    private lateinit var mRevealPanelAdapter: RevealPanelAdapter
    private lateinit var mTrainerViewModel: TrainerViewModel
    private lateinit var mContentViewModel: ContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_learning)

        mContentViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(ContentViewModel::class.java)

        mRevealPanelAdapter = RevealPanelAdapter(
            this.layoutInflater,
            application_learning_root,
            ::handleReportContent
        )

        val trainerFactory = InjectorUtils.provideTrainerViewModelFactory()
        mTrainerViewModel = ViewModelProviders.of(this, trainerFactory)
            .get(TrainerViewModel::class.java)

        init()
    }

    private fun init() {
        application_learning_reveal.setOnClickListener {
            mRevealPanelAdapter.show()
        }

        application_learning_home_button.setOnClickListener {
            val intent =
                Intent(this@ApplicationLearningActivity, DashboardActivity::class.java)
            startActivity(intent)
        }

        mTrainerViewModel.getTrainer().observeOnce(this, Observer { trainer ->
            application_learning_translation.text = trainer.content
            mRevealPanelAdapter.setContent(trainer.translation, unidecode(trainer.translation))

            application_learning_next_button.setOnClickListener {
                val completed = validate(trainer)
                if (completed) {
                    val intent =
                        Intent(
                            this@ApplicationLearningActivity,
                            SemanticLearningActivity::class.java
                        )
                    startActivity(intent)
                }
            }
        })
    }

    private fun validate(trainer: Trainer): Boolean {
        val transliterationDone = application_learning_transliteration.text.isNotBlank()
        val sentenceOneDone = Validation.validate(
            application_learning_sentence_1.text.toString(),
            trainer.translation
        )
        val sentenceTwoDone = Validation.validate(
            application_learning_sentence_2.text.toString(),
            trainer.translation
        )
        val sentenceThreeDone = Validation.validate(
            application_learning_sentence_3.text.toString(),
            trainer.translation
        )

        return transliterationDone && sentenceOneDone && sentenceTwoDone && sentenceThreeDone
    }

    private fun handleReportContent() {
        mTrainerViewModel.getTrainer().observeOnce(this, Observer { trainer ->
            mContentViewModel.delete(trainer.contentObj)

            val intent =
                Intent(this@ApplicationLearningActivity, DashboardActivity::class.java)
            startActivity(intent)
        })
    }
}
