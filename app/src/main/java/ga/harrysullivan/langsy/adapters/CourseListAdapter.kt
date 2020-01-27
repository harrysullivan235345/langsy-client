package ga.harrysullivan.langsy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import ga.harrysullivan.langsy.R
import ga.harrysullivan.langsy.models.Course
import ga.harrysullivan.langsy.utils.CourseList

class CourseListAdapter(
    inflater: LayoutInflater,
    parent: ViewGroup,
    courses: List<Course>,
    callback: (course: Course) -> Unit
) {
//    private val dummyData = arrayOf("thins", "other this", "elsa", "hwwer", "foxes", "elsa")

    init {
        courses.forEach { course ->
            run {
                val courseLabel = inflater.inflate(R.layout.dashboard_course_label, parent, false)
                courseLabel.findViewById<TextView>(R.id.course_label_language).text =
                    CourseList.localFromCode(course.language)
                courseLabel.setOnClickListener {
                    callback(course)
                }
                parent.addView(courseLabel)
            }
        }
    }
}