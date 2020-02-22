package ga.harrysullivan.langsy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ga.harrysullivan.langsy.R
import kotlin.reflect.KFunction0

class RevealPanelAdapter(
    inflater: LayoutInflater,
    parent: ViewGroup,
    onReport: KFunction0<Unit>
) {
    private val mPanel: View = inflater.inflate(R.layout.reveal_panel, parent, false)
    private val mOnReport = onReport

    init {
        hide()

        setListeners()
        parent.addView(mPanel)
    }

    private fun setListeners() {
        mPanel.findViewById<TextView>(R.id.reveal_panel_hide).setOnClickListener {
            hide()
        }

        mPanel.findViewById<TextView>(R.id.reveal_panel_report).setOnLongClickListener {
            mOnReport()
            true
        }
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