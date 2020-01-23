package ga.harrysullivan.langsy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import ga.harrysullivan.langsy.adapters.CourseListAdapter

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val inflater = this.layoutInflater
        val main = findViewById<LinearLayout>(R.id.dashboard_courselist)

        val courseLabel = inflater.inflate(R.layout.course_label, main, false)
        main.addView(courseLabel)

//        val listView = findViewById<ListView>(R.id.dashboard_course_list)
//        listView.adapter = CourseListAdapter(this)
//        listView.setDivider(null)
    }
}
