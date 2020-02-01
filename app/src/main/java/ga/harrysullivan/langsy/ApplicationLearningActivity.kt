package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ga.harrysullivan.langsy.adapters.RevealPanelAdapter
import ga.harrysullivan.langsy.utils.InjectorUtils
import ga.harrysullivan.langsy.utils.observeOnce
import ga.harrysullivan.langsy.view_models.TrainerViewModel
import kotlinx.android.synthetic.main.activity_application_learning.*
import kotlinx.android.synthetic.main.activity_semantic_learning.*
import kotlinx.android.synthetic.main.activity_visual_learning.*
import net.gcardone.junidecode.Junidecode.unidecode

class ApplicationLearningActivity : AppCompatActivity() {

    private lateinit var mRevealPanelAdapter: RevealPanelAdapter
    private lateinit var mTrainerViewModel: TrainerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_learning)

        mRevealPanelAdapter = RevealPanelAdapter(this.layoutInflater, application_learning_root)

        val trainerFactory = InjectorUtils.provideTrainerViewModelFactory()
        mTrainerViewModel = ViewModelProviders.of(this, trainerFactory)
            .get(TrainerViewModel::class.java)

        init()
    }

    private fun init() {
        application_learning_reveal.setOnClickListener{
            mRevealPanelAdapter.show()
        }

        application_learning_next_button.setOnClickListener {
            val completed = validate()
            if (completed) {
                val intent =
                    Intent(this@ApplicationLearningActivity, SemanticLearningActivity::class.java)
                startActivity(intent)
            }
        }

        application_learning_home_button.setOnClickListener {
            val intent =
                Intent(this@ApplicationLearningActivity, DashboardActivity::class.java)
            startActivity(intent)
        }

        mTrainerViewModel.getTrainer().observeOnce(this, Observer {
            application_learning_translation.text = it.content
            mRevealPanelAdapter.setContent(it.translation, unidecode(it.translation))
        })
    }

    private fun validate(): Boolean {
        val transliterationDone = application_learning_transliteration.text.isNotBlank()
        val sentenceOneDone = application_learning_sentence_1.text.isNotBlank()
        val sentenceTwoDone = application_learning_sentence_2.text.isNotBlank()
        val sentenceThreeDone = application_learning_sentence_3.text.isNotBlank()

        return transliterationDone && sentenceOneDone && sentenceTwoDone && sentenceThreeDone
    }
}
