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
import kotlinx.android.synthetic.main.activity_visual_learning.*
import net.gcardone.junidecode.Junidecode.unidecode

class VisualLearningActivity : AppCompatActivity() {

    private lateinit var mRevealPanelAdapter: RevealPanelAdapter
    private lateinit var mTrainerViewModel: TrainerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visual_learning)

        mRevealPanelAdapter = RevealPanelAdapter(this.layoutInflater, visual_learning_root)

        val trainerFactory = InjectorUtils.provideTrainerViewModelFactory()
        mTrainerViewModel = ViewModelProviders.of(this, trainerFactory)
            .get(TrainerViewModel::class.java)

        init()
    }

    private fun init() {
        mTrainerViewModel.getTrainer().observeOnce(this, Observer {
            visual_learning_translation.text = it.translation
            mRevealPanelAdapter.setContent(it.content, unidecode(it.translation))
        })

        setListeners()
    }

    private fun setListeners() {
        visual_learning_reveal.setOnClickListener {
            mRevealPanelAdapter.show()
        }

        visual_learning_next_button.setOnClickListener {
            val intent =
                Intent(this@VisualLearningActivity, ApplicationLearningActivity::class.java)
            startActivity(intent)
        }
        
        visual_learning_home_button.setOnClickListener {
            val intent =
                Intent(this@VisualLearningActivity, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}
