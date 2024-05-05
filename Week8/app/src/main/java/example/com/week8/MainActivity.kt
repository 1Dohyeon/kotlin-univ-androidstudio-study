package example.com.week8

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import example.com.week8.databinding.ActivityMainBinding
import java.io.IOException
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    fun readDiary(fName: String) : String?{
        var diaryStr: String? = null
        try {
            openFileInput(fName).bufferedReader().useLines { lines ->
                diaryStr = lines.joinToString("\n")
            }
        } catch (e: IOException) {
            e.printStackTrace() // 에러 처리를 위해 스택 트레이스를 출력
        }
        return diaryStr
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val context = applicationContext
        val TAG = "Debug"

        var cal = Calendar.getInstance()
        var cy = cal.get(Calendar.YEAR)
        var cm = cal.get(Calendar.MONTH)
        var cd = cal.get(Calendar.DAY_OF_MONTH)
        lateinit var fName: String

        binding.datePciker.init(cy, cm, cd) { view, year, month, day ->
            // 날짜가 변경될 때 실행되는 코드
            fName = String.format("%04d_%02d_%02d.txt", year, month + 1, day) // 파일 이름 설정
            val diaryContent = readDiary(fName) // 해당 날짜의 일기 내용을 읽어옴
            binding.editText.setText(diaryContent ?: "") // 일기 내용을 EditText에 표시
            binding.updateBtn.visibility = if (diaryContent != null) View.VISIBLE else View.INVISIBLE // 수정 버튼 표시 여부 설정
        }

        binding.smtBtn.setOnClickListener {
            val diaryContent = binding.editText.text.toString() // EditText의 내용을 문자열로 가져옴
            openFileOutput(fName, Context.MODE_PRIVATE).use { output ->
                output.write(diaryContent.toByteArray()) // 일기 내용을 파일에 저장
            }
        }

    }
}