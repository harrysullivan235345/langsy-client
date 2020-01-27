package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ga.harrysullivan.langsy.adapters.RevealPanelAdapter
import ga.harrysullivan.langsy.utils.InjectorUtils
import ga.harrysullivan.langsy.view_models.TrainerViewModel
import kotlinx.android.synthetic.main.activity_semantic_learning.*
import kotlinx.android.synthetic.main.activity_visual_learning.*
import kotlinx.android.synthetic.main.activity_visual_learning.visual_learning_next_button
import kotlinx.android.synthetic.main.activity_visual_learning.visual_learning_reveal

class VisualLearningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visual_learning)

        val revealPanelAdapter = RevealPanelAdapter(this.layoutInflater, visual_learning_root)

        visual_learning_reveal.setOnClickListener{
            revealPanelAdapter.show()
        }

        visual_learning_next_button.setOnClickListener {
            val intent =
                Intent(this@VisualLearningActivity, ApplicationLearningActivity::class.java)
            startActivity(intent)
        }

        val trainerFactory = InjectorUtils.provideTrainerViewModelFactory()
        val viewModel = ViewModelProviders.of(this, trainerFactory)
            .get(TrainerViewModel::class.java)

        viewModel.getTrainer().observe(this, Observer {
            visual_learning_translation.text = it.translation
        })
    }
}
