package example.univ.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import example.univ.myapplication.databinding.ExampleFragmentBinding

class  ExampleFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ExampleFragmentBinding.inflate(inflater, container, false).root
    }
}