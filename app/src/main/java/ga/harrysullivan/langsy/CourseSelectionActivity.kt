package ga.harrysullivan.langsy

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ga.harrysullivan.langsy.adapters.CourseSelectionAdapter
import kotlinx.android.synthetic.main.activity_course_selection.*

class CourseSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_selection)

        val languages = listOf<List<String>>(
            listOf<String>("sv", "svenska"),
            listOf<String>("ar", "العربية"),
            listOf<String>("de", "deutsch"),
            listOf<String>("fr", "français"),
            listOf<String>("ru", "русский"),
            listOf<String>("it", "Italiano"),
            listOf<String>("es", "Español"),
            listOf<String>("pl", "Polski"),
            listOf<String>("ja", "日本語"),
            listOf<String>("pt", "Português"),
            listOf<String>("uk", "українська"),
            listOf<String>("fa", "فارسی"),
            listOf<String>("hi", "हिन्दी"),
            listOf<String>("tr", "türkçe"),
            listOf<String>("th", "ไทย"),
            listOf<String>("sw", "kiswahili")
        )

        CourseSelectionAdapter(this.layoutInflater, course_selection_list, languages)
    }
}
