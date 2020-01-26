package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ga.harrysullivan.langsy.adapters.RevealPanelAdapter
import kotlinx.android.synthetic.main.activity_assessment.*
import kotlinx.android.synthetic.main.activity_semantic_learning.*
import kotlinx.android.synthetic.main.activity_semantic_learning.semantic_learning_reveal

class SemanticLearningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semantic_learning)

        val revealPanelAdapter = RevealPanelAdapter(this.layoutInflater, semantic_learning_root)

        semantic_learning_reveal.setOnClickListener{
            revealPanelAdapter.show()
        }

        semantic_learning_next_button.setOnClickListener {
            val intent =
                Intent(this@SemanticLearningActivity, AssessmentActivity::class.java)
            startActivity(intent)
        }
    }
}
