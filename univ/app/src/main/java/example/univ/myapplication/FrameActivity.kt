package example.univ.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import example.univ.myapplication.databinding.FrameLayoutBinding

class FrameActivity : AppCompatActivity() {
    // 작동시키려면 아래 코드 MainActivity 로 복붙하면 됨.
    lateinit var binding: FrameLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FrameLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.redButton.setOnClickListener {
            binding.redView.visibility = View.VISIBLE
            binding.greenView.visibility = View.INVISIBLE
            binding.blueView.visibility = View.INVISIBLE
        }

        binding.greenButton.setOnClickListener {
            binding.redView.visibility = View.INVISIBLE
            binding.greenView.visibility = View.VISIBLE
            binding.blueView.visibility = View.INVISIBLE
        }

        binding.blueButton.setOnClickListener {
            binding.redView.visibility = View.INVISIBLE
            binding.greenView.visibility = View.INVISIBLE
            binding.blueView.visibility = View.VISIBLE
        }
    }
}
