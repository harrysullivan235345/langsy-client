package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_visual_learning.*

class VisualLearningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visual_learning)

        visual_learning_next_button.setOnClickListener {
            val intent =
                Intent(this@VisualLearningActivity, ApplicationLearningActivity::class.java)
            startActivity(intent)
        }
    }
}
