package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ga.harrysullivan.langsy.adapters.RevealPanelAdapter
import kotlinx.android.synthetic.main.activity_application_learning.*
import kotlinx.android.synthetic.main.activity_semantic_learning.*

class ApplicationLearningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_learning)

        val revealPanelAdapter = RevealPanelAdapter(this.layoutInflater, application_learning_root)

        application_learning_reveal.setOnClickListener{
            revealPanelAdapter.show()
        }

        application_learning_next_button.setOnClickListener {
            val intent =
                Intent(this@ApplicationLearningActivity, SemanticLearningActivity::class.java)
            startActivity(intent)
        }
    }
}
