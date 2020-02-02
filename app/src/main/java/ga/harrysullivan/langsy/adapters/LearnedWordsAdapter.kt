package ga.harrysullivan.langsy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import ga.harrysullivan.langsy.R
import ga.harrysullivan.langsy.models.Content
import ga.harrysullivan.langsy.models.Course
import ga.harrysullivan.langsy.utils.CourseList

class LearnedWordsAdapter(
    inflater: LayoutInflater,
    parent: ViewGroup,
    contents: List<Content>
) {
//    private val dummyData = arrayOf("thins", "other this", "elsa", "hwwer", "foxes", "elsa")

    init {
        contents.forEach { content ->
            val courseLabel = inflater.inflate(R.layout.learned_words_label, parent, false)
            courseLabel.findViewById<TextView>(R.id.learned_word_label_content).text = content.uid.toString()
            parent.addView(courseLabel)

        }
    }
}