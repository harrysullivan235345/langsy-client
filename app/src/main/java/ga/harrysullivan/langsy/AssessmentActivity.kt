package ga.harrysullivan.langsy

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import ga.harrysullivan.langsy.adapters.CorrectAnswerAdapter
import ga.harrysullivan.langsy.adapters.RevealPanelAdapter
import ga.harrysullivan.langsy.adapters.SpontaneousRecallAdapter
import kotlinx.android.synthetic.main.activity_assessment.*


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

        val spontaneousRecallAdapter = SpontaneousRecallAdapter(this.layoutInflater, assessment_root)
        Handler().postDelayed({
            spontaneousRecallAdapter.show()
        }, 1000)
    }
}
