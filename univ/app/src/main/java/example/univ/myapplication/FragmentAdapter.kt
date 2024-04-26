package example.univ.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val TAG_NUMS = 2

/** adapter를 통해 position으로 flag를 넘겨서 불러옴 */
class FragmentAdapter(fgManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fgManager, lifecycle) {
    override fun getItemCount(): Int = TAG_NUMS
    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return ExampleFragment()
            1 -> return  ExampleFragmentTwo()
        }
        return ExampleFragment()
    }
}