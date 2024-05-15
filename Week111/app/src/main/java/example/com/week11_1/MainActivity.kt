package example.com.week11_1

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
    
    class SimplePainter(context: Context): View(context) {
        var startX = -1f
        var startY = -1f
        var stopX = -1f
        var stopY = -1f
        val paint = Paint()

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            when(event?.action) {
                // 커서를 누른 경우
                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                    startY = event.y
                }
                // 움직였다가, 커서를 땐 경우
                MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                    stopX = event.x;
                    stopY = event.y
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
            when(curShape) {
                LINE -> {
                    canvas.drawLine(startX, startY, stopX, stopY, paint)
                }
                CIRCLE -> {
                    val radius = sqrt((stopX - startX).toDouble().pow(2.0)) +
                            (stopY - startY).toDouble().pow(2.0)
                    canvas.drawCircle(startX, startY, radius.toFloat()/50, paint)
                }
            }
        }
    }

    companion object{
        const val LINE = 1
        const val CIRCLE = 2
        var curShape = LINE
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //             재정의한 클래스 이름(this)
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