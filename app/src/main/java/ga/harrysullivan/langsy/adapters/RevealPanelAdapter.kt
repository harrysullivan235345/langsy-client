package ga.harrysullivan.langsy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ga.harrysullivan.langsy.R

class RevealPanelAdapter(inflater: LayoutInflater, parent: ViewGroup) {
    private val mPanel: View

    init {
        mPanel = inflater.inflate(R.layout.reveal_panel, parent, false)
        hide()

        mPanel.findViewById<TextView>(R.id.reveal_panel_hide).setOnClickListener {
            hide()
        }
        parent.addView(mPanel)
    }

    fun setContent(big: String, small: String) {
        mPanel.findViewById<TextView>(R.id.big_text).text = big
        mPanel.findViewById<TextView>(R.id.small_text).text = small
    }

    fun show() {
        mPanel.visibility = View.VISIBLE
    }

    fun hide() {
        mPanel.visibility = View.INVISIBLE
    }
}