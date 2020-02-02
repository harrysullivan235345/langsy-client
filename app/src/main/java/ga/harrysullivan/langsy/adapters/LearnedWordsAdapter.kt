package ga.harrysullivan.langsy.adapters

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import ga.harrysullivan.langsy.R
import ga.harrysullivan.langsy.models.Content
import ga.harrysullivan.langsy.models.Course
import ga.harrysullivan.langsy.utils.Corpora
import ga.harrysullivan.langsy.utils.CourseList

data class File(val type: String, val pos: String)

class LearnedWordsAdapter(
    inflater: LayoutInflater,
    parent: ViewGroup,
    contents: List<Content>,
    application: Application
) {
//    private val dummyData = arrayOf("thins", "other this", "elsa", "hwwer", "foxes", "elsa")
    private val mApplication: Application = application
    init {

        val filledContents = fill(contents)

        filledContents.forEach { content ->
            val courseLabel = inflater.inflate(R.layout.learned_words_label, parent, false)
            courseLabel.findViewById<TextView>(R.id.learned_word_label_content).text = content.toString()
            parent.addView(courseLabel)
        }
    }

    private fun fill(contents: List<Content>): List<String> {
        val distinct = contents.distinctBy {
            arrayOf(it.type, it.partOfSpeech)
        }
        val fileDatas = mutableMapOf<String, List<String>>()

        distinct.forEach { content ->
            val fileData = Corpora(mApplication).getFile(content.type, content.partOfSpeech)
            fileDatas[content.partOfSpeech] =  fileData
        }

        val filled = contents.map { content ->
            val file = fileDatas[content.partOfSpeech]
            file!![content.line]
        }

        return filled
    }
}