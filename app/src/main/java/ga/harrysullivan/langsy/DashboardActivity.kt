package ga.harrysullivan.langsy

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import ga.harrysullivan.langsy.adapters.CourseListAdapter

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val courseList = findViewById<LinearLayout>(R.id.dashboard_courselist)
        CourseListAdapter(this.layoutInflater, courseList)
    }
}
