package ga.harrysullivan.langsy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ga.harrysullivan.langsy.adapters.CorrectAnswerAdapter
import ga.harrysullivan.langsy.adapters.RevealPanelAdapter
import kotlinx.android.synthetic.main.activity_assessment.*
import kotlinx.android.synthetic.main.activity_semantic_learning.*

class AssessmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assessment)

        val revealPanelAdapter = RevealPanelAdapter(this.layoutInflater, assessment_root)

        assessment_reveal.setOnClickListener {
            revealPanelAdapter.show()
        }

        val correctAnswerAdapter = CorrectAnswerAdapter(this.layoutInflater, assessment_root)
        assessment_next_button.setOnClickListener {
            correctAnswerAdapter.show()
        }
    }
}
