package example.univ.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import example.univ.myapplication.databinding.ActivityMainBinding
import example.univ.myapplication.databinding.TablayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: TablayoutBinding
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TablayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** Tool Bar 의 menu */
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(androidx.appcompat.R.drawable.abc_btn_colored_material)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        /** Tab: adapter 없이 */
//        val tab = binding.tab
//        val tab1: TabLayout.Tab = tab.newTab()
//        tab1.text = "tab1"
//        tab.addTab(tab1)
//        tab.addTab(tab.newTab().setText("tab2"))
//        tab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                val transaction = supportFragmentManager.beginTransaction()
//                when(tab?.text){
//                    "tab1" -> transaction.replace(R.id.tabContent, ExampleFragment())
//                    "tab2" -> transaction.replace(binding.tabContent.id, ExampleFragmentTwo())
//                }
//                transaction.commit()
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//                /*   */
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                /*   */
//            }
//        })

        /** Tab: viewpager와 adapter 활용 */
        val tabLayout = binding.tab
        val viewPage = binding.viewpager
        viewPage.adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, viewPage) {tab, position ->
            tab.text = "Tab $position"
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                Log.d(TAG, "item1 clicked")
            }
            R.id.item2 -> {
                Log.d(TAG, "item2 clicked")
            }
            R.id.item3 -> {
                Log.d(TAG, "item3 clicked")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}