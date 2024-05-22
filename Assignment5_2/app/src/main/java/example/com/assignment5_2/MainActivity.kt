package example.com.assignment5_2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    class SimplePainter(context: Context) : View(context) {
        var startX = -1f
        var startY = -1f
        var stopX = -1f
        var stopY = -1f
        val paint = Paint()

        // 도형 정보를 저장할 리스트
        val shapes = ArrayList<DrawArr>()

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            when (event?.action) {
                // 커서를 누른 경우
                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                    startY = event.y
                }
                // 커서를 땐 경우
                MotionEvent.ACTION_UP -> {
                    stopX = event.x
                    stopY = event.y
                    // 도형 정보를 리스트에 추가
                    shapes.add(DrawArr(curShape, startX, startY, stopX, stopY))
                    this.invalidate()
                }
            }
            return true
        }

        // 화면에 그려질 내용을 onDraw에 코딩
        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            paint.isAntiAlias = true
            paint.strokeWidth = 5f
            paint.style = Paint.Style.STROKE
            paint.color = Color.RED
            // 저장된 모든 도형을 그리기
            for (shape in shapes) {
                when (shape.type) {
                    LINE -> {
                        canvas.drawLine(shape.startX, shape.startY, shape.stopX, shape.stopY, paint)
                    }
                    CIRCLE -> {
                        val radius = sqrt((shape.stopX - shape.startX).toDouble().pow(2.0) +
                                (shape.stopY - shape.startY).toDouble().pow(2.0))
                        canvas.drawCircle(shape.startX, shape.startY, radius.toFloat(), paint)
                    }
                }
            }
            when (curShape) {
                LINE -> {
                    canvas.drawLine(startX, startY, stopX, stopY, paint)
                }
                CIRCLE -> {
                    val radius = sqrt((stopX - startX).toDouble().pow(2.0) +
                            (stopY - startY).toDouble().pow(2.0))
                    canvas.drawCircle(startX, startY, radius.toFloat(), paint)
                }
            }
        }
    }

    companion object {
        const val LINE = 1
        const val CIRCLE = 2
        var curShape = LINE
    }

    // 도형 정보를 저장할 클래스
    data class DrawArr(val type: Int, val startX: Float, val startY: Float, val stopX: Float, val stopY: Float)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 재정의한 클래스 이름(this)
        setContentView(SimplePainter(this))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_draw_line -> {
                curShape = LINE
                true
            }
            R.id.menu_draw_circle -> {
                curShape = CIRCLE
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}