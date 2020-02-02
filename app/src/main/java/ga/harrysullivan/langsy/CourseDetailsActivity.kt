package ga.harrysullivan.langsy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_course_details.*

class CourseDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)

        course_details_big_number.setOnClickListener {
            val intent =
                Intent(this@CourseDetailsActivity, LearnedWordsActivity::class.java)
            startActivity(intent)
        }
    }
}
