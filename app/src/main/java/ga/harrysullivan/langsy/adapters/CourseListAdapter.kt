package ga.harrysullivan.langsy.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import ga.harrysullivan.langsy.R

class CourseListAdapter(inflater: LayoutInflater, parent: ViewGroup) {
    private val dummyData = arrayOf("thins", "other this", "elsa", "hwwer", "foxes", "elsa")

    init {
        dummyData.forEach {data -> run{
            val courseLabel = inflater.inflate(R.layout.course_label, parent, false)
            courseLabel.findViewById<TextView>(R.id.course_label_language).text = data
            parent.addView(courseLabel)
        }}
    }
}