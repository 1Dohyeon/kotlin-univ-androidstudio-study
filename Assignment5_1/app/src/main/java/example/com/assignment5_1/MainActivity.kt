package example.com.assignment5_1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import example.com.assignment5_1.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val TAG = "MainActivity Debug"

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    private fun readDiary(fName: String): String? {
        var diaryStr: String? = null
        try {
            openFileInput(fName).bufferedReader().useLines { lines ->
                diaryStr = lines.fold("") { some, text ->
                    "$some\n$text"
                }.trim()
                Log.d(TAG, "$diaryStr")
            }
        } catch (e: Exception) {
            // 에러 처리를 위해 스택 트레이스를 출력
            e.printStackTrace()
        }
        return diaryStr
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val context = applicationContext

        // 날짜 가져옴
        val cal = Calendar.getInstance()
        val cy = cal.get(Calendar.YEAR)
        val cm = cal.get(Calendar.MONTH)
        val cd = cal.get(Calendar.DAY_OF_MONTH)
        
        // 오늘날짜로 초기화
        var fName = String.format("%04d_%02d_%02d.txt", cy, cm + 1, cd) 

        // Read today's diary content on app start
        val diaryContent = readDiary(fName)
        if(diaryContent != null) {
            binding.editText.setText(diaryContent)
            binding.updateBtn.visibility = View.VISIBLE
        } else {
            binding.editText.setText("")
            binding.updateBtn.visibility = View.INVISIBLE
        }

        /** 날짜가 변경될 때 실행.
         * 날짜마다 파일이 있다면, 파일의 내용을 보여줌 */
        binding.datePciker.init(cy, cm, cd) { view, year, month, day ->
            fName = String.format("%04d_%02d_%02d.txt", year, month + 1, day) // 파일 이름 설정
            val diaryContent = readDiary(fName) // 해당 날짜의 일기 내용을 읽어옴

            if(diaryContent != null) { // 불러올 파일이 있다면(작성했던 다이어리가 있다면)
                binding.editText.setText(diaryContent) // 파일 내용을 EditText에 표시
                binding.updateBtn.visibility = View.VISIBLE // 수정 하기 버튼이 보이도록 함
            } else { // 파일이 없다면
                binding.editText.setText("") // 아무런 텍스트도 설정하지 않음 -> hint가 보이게 됨
                binding.updateBtn.visibility = View.INVISIBLE // 수정 하기 버튼 안보이게 설정
            }
        }

        /** 새로 저장 버튼을 눌렀을 경우 실행.
         * editText에 적힌 문자열을 파일에 그대로 작성하여 저장  */
        binding.smtBtn.setOnClickListener {
            val diaryContent = binding.editText.text.toString() // EditText의 내용을 문자열로 가져옴
            openFileOutput(fName, Context.MODE_PRIVATE).use {
                it.write(diaryContent.toByteArray()) // 일기 내용을 파일에 저장
            }
        }

        /** 수정 하기 버튼을 눌렀을 경우 실행.
         * editText에 적힌 문자열을 단순히 파일에 저장하기에 smtBtn 리스너와 같음  */
        binding.updateBtn.setOnClickListener {
            val diaryContent = binding.editText.text.toString() // EditText의 내용을 문자열로 가져옴
            openFileOutput(fName, Context.MODE_PRIVATE).use {
                it.write(diaryContent.toByteArray()) // 일기 내용을 파일에 저장
            }
        }
    }
}
