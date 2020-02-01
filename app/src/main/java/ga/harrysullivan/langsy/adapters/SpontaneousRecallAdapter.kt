package ga.harrysullivan.langsy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import ga.harrysullivan.langsy.R
import org.w3c.dom.Text

class SpontaneousRecallAdapter(inflater: LayoutInflater, parent: ViewGroup) {
    private val mPanel: View
    init {
        mPanel = inflater.inflate(R.layout.spontaneous_recall, parent, false)
        hide()

        mPanel.findViewById<TextView>(R.id.spontaneous_recall_close_button).setOnClickListener {
            hide()
        }

        parent.addView(mPanel)
    }

    fun setContent(content: String) {
        mPanel.findViewById<TextView>(R.id.assessment_home_button).text = content
    }

    fun show() {
        mPanel.visibility = View.VISIBLE
    }

    fun hide() {
        mPanel.visibility = View.INVISIBLE
    }
}