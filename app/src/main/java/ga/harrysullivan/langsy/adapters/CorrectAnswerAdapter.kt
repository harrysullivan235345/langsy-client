package ga.harrysullivan.langsy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ga.harrysullivan.langsy.R

class CorrectAnswerAdapter(inflater: LayoutInflater, parent: ViewGroup) {
    private val mPanel: View

    init {
        mPanel = inflater.inflate(R.layout.correct_answer_panel, parent, false)
        hide()

        parent.addView(mPanel)
    }

    fun setContent(translation: String) {
        mPanel.findViewById<TextView>(R.id.correct_answer_panel_translation).text = translation
    }

    fun setCallback(onClickListener: View.OnClickListener) {
        mPanel.findViewById<TextView>(R.id.correct_panel_hide).setOnClickListener(onClickListener)
    }

    fun show() {
        mPanel.visibility = View.VISIBLE
    }

    fun hide() {
        mPanel.visibility = View.INVISIBLE
    }
}