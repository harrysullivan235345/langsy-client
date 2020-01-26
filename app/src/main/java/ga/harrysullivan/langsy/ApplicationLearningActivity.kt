package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_application_learning.*

class ApplicationLearningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_learning)

        application_learning_next_button.setOnClickListener {
            val intent =
                Intent(this@ApplicationLearningActivity, SemanticLearningActivity::class.java)
            startActivity(intent)
        }
    }
}
