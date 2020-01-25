package ga.harrysullivan.langsy.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class PaintView : View {

    private val path = Path()
    private val brush = Paint()

    constructor(context: Context) : super(context) {
        init(context)
    }

    fun init(context: Context) {
        brush.isAntiAlias = false
        brush.color = Color.WHITE
        brush.style = Paint.Style.STROKE
        brush.strokeJoin = Paint.Join.ROUND
        brush.strokeWidth = 8f

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(context)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(pointX, pointY)
                return true
            }
            MotionEvent.ACTION_MOVE -> path.lineTo(pointX, pointY)
            else -> return false
        }

        postInvalidate()
        return false
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, brush)
    }
}
