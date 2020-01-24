package ga.harrysullivan.langsy

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import ga.harrysullivan.langsy.adapters.CourseListAdapter
import ga.harrysullivan.langsy.utils.Insults
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_main.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        CourseListAdapter(this.layoutInflater, dashboard_courselist)

        val insult = Insults(application).getInsult()
        dasboard_add_course_button.text = "add course ${insult}"

        dasboard_add_course_button.setOnClickListener {
            val intent = Intent(this@DashboardActivity, CourseSelectionActivity::class.java)
            startActivity(intent)
        }

    }
}
