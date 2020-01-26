package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ga.harrysullivan.langsy.adapters.CourseListAdapter
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        CourseListAdapter(this.layoutInflater, dashboard_courselist)

        dasboard_add_course_button.setOnClickListener {
            val intent = Intent(this@DashboardActivity, CourseSelectionActivity::class.java)
            startActivity(intent)
        }

    }
}
