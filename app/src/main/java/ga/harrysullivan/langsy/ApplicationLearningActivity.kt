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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_learning)

        val revealPanelAdapter = RevealPanelAdapter(this.layoutInflater, application_learning_root)

        application_learning_reveal.setOnClickListener{
            revealPanelAdapter.show()
        }

        application_learning_next_button.setOnClickListener {
            val completed = validate()
            if (completed) {
                val intent =
                    Intent(this@ApplicationLearningActivity, SemanticLearningActivity::class.java)
                startActivity(intent)
            }
        }

        val trainerFactory = InjectorUtils.provideTrainerViewModelFactory()
        val viewModel = ViewModelProviders.of(this, trainerFactory)
            .get(TrainerViewModel::class.java)

        viewModel.getTrainer().observeOnce(this, Observer {
            application_learning_translation.text = it.translation
            revealPanelAdapter.setContent(it.content, unidecode(it.translation))
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
