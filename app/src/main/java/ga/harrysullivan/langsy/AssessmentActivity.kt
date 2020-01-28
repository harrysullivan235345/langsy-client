package ga.harrysullivan.langsy

import android.os.Bundle
import android.os.Handler
import android.widget.Space
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ga.harrysullivan.langsy.adapters.CorrectAnswerAdapter
import ga.harrysullivan.langsy.adapters.RevealPanelAdapter
import ga.harrysullivan.langsy.adapters.SpontaneousRecallAdapter
import ga.harrysullivan.langsy.constants.SpacedRepetition
import ga.harrysullivan.langsy.utils.InjectorUtils
import ga.harrysullivan.langsy.utils.observeOnce
import ga.harrysullivan.langsy.view_models.ContentViewModel
import ga.harrysullivan.langsy.view_models.TrainerViewModel
import kotlinx.android.synthetic.main.activity_assessment.*
import kotlinx.android.synthetic.main.activity_semantic_learning.*
import net.gcardone.junidecode.Junidecode
import net.gcardone.junidecode.Junidecode.unidecode


class AssessmentActivity : AppCompatActivity() {

    private lateinit var mContentViewModel: ContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assessment)

        mContentViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(ContentViewModel::class.java)

        val revealPanelAdapter = RevealPanelAdapter(this.layoutInflater, assessment_root)
        assessment_reveal.setOnClickListener {
            revealPanelAdapter.show()
        }

        var dirty = false

        val correctAnswerAdapter = CorrectAnswerAdapter(this.layoutInflater, assessment_root)
        assessment_next_button.setOnClickListener {
            correctAnswerAdapter.show()
        }

//        val spontaneousRecallAdapter = SpontaneousRecallAdapter(this.layoutInflater, assessment_root)
//        Handler().postDelayed({
//            spontaneousRecallAdapter.show()
//        }, 3000)

        val trainerFactory = InjectorUtils.provideTrainerViewModelFactory()
        val viewModel = ViewModelProviders.of(this, trainerFactory)
            .get(TrainerViewModel::class.java)

        viewModel.getTrainer().observeOnce(this, Observer {trainer ->
            assessment_content.text = trainer.content
            assessment_stage.text = toPct(trainer.contentObj.stage.toDouble() / SpacedRepetition.THRESHOLD_OF_MASTERY.toDouble())
            revealPanelAdapter.setContent(trainer.translation, unidecode(trainer.translation))
            correctAnswerAdapter.setContent(trainer.translation)

            assessment_next_button.setOnClickListener {
                val userInput = assessment_edit_text.text.toString().toLowerCase()
                if (userInput == trainer.translation.toLowerCase()) {
                    if (dirty) {
                        correctAnswerAdapter.show()
                    } else {
                        mContentViewModel.addToStage(trainer.contentObj.uid, 1)
                        correctAnswerAdapter.show()
                    }

                } else {
                    revealPanelAdapter.show()
                    dirty = true
                }
            }
        })
    }

    private fun toPct(n: Double): String {
        return "${(n*100).toInt()}%"
    }
}
