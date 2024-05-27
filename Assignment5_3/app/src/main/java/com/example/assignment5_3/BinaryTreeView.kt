package com.example.assignment5_3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BinaryTreeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = Color.YELLOW
        strokeWidth = 5f
        textSize = 50f
        isAntiAlias = true
    }

    private val circlePaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val linePaint = Paint().apply {
        color = Color.GRAY
        strokeWidth = 5f
        isAntiAlias = true
    }

    var root: Node? = null
        set(value) {
            field = value
            invalidate()
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = 500
        val desiredHeight = 700

        val width = resolveSize(desiredWidth, widthMeasureSpec)
        val height = resolveSize(desiredHeight, heightMeasureSpec)

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        root?.let { drawNode(canvas, it, width / 2f, 200f, width / 4f) }
    }

    private fun drawNode(canvas: Canvas, node: Node?, x: Float, y: Float, offset: Float) {
        if (node == null) return

        // text를 둘러싼 원 그림
        canvas.drawCircle(x, y, 50f, circlePaint)

        // text를 그림
        val text = node.value.toString()
        val textWidth = paint.measureText(text)
        val textX = x - textWidth / 2
        val textY = y + paint.textSize / 4
        canvas.drawText(text, textX, textY, paint)

        // 노드를 연결하는 선을 그림
        if (node.left != null) {
            canvas.drawLine(x, y + 50, x - offset, y + 150, linePaint)
            drawNode(canvas, node.left, x - offset, y + 150, offset / 2)
        }
        if (node.right != null) {
            canvas.drawLine(x, y + 50, x + offset, y + 150, linePaint)
            drawNode(canvas, node.right, x + offset, y + 150, offset / 2)
        }
    }
}
