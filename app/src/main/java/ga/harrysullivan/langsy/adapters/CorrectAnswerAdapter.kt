package ga.harrysullivan.langsy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import ga.harrysullivan.langsy.R
import org.w3c.dom.Text

class CorrectAnswerAdapter(inflater: LayoutInflater, parent: ViewGroup) {
    private val mPanel: View
    init {
        mPanel = inflater.inflate(R.layout.correct_answer_panel, parent, false)
        hide()

        parent.addView(mPanel)
    }

    fun show() {
        mPanel.visibility = View.VISIBLE
    }

    fun hide() {
        mPanel.visibility = View.INVISIBLE
    }
}