package ga.harrysullivan.langsy.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ga.harrysullivan.langsy.CourseSelectionActivity
import ga.harrysullivan.langsy.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class CourseSelectionAdapter(
    inflater: LayoutInflater,
    parent: ViewGroup,
    languages: List<List<String>>,
    callback: (langCode: String) -> Unit
) {

    init {
        languages.forEach { data ->
            run {
                val courseLabel = inflater.inflate(R.layout.course_selection_row, parent, false)
                val languageLabel = courseLabel.findViewById<TextView>(R.id.course_language)
                languageLabel.text = data[1]
                languageLabel.setOnClickListener {
                    callback(data[0])
                }
                parent.addView(courseLabel)
            }
        }
    }
}