package ga.harrysullivan.langsy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import ga.harrysullivan.langsy.R
import org.w3c.dom.Text

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

    fun setContent(content: String) {
        mPanel.findViewById<TextView>(R.id.big_text).text = content
    }

    fun show() {
        mPanel.visibility = View.VISIBLE
    }

    fun hide() {
        mPanel.visibility = View.INVISIBLE
    }
}