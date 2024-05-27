package com.example.assignment5_3

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment5_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var binaryTreeView: BinaryTreeView
    private val arr = ArrayList<Int>().apply {
        add(0)
        add(9)
        add(8)
        add(7)
        add(6)
        add(5)
        add(4)
        add(3)
        add(2)
        add(1)
        add(0)
    }

    private fun maxHeapTree() {
        val tree = BinaryTree();
        val root = tree.createBinaryTree(arr.toTypedArray())
        binaryTreeView.root = root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binaryTreeView = binding.binaryTreeView
        val textView: TextView = binding.textView
        val popButton: Button = binding.popButton
        val pushButton: Button = binding.pushButton
        val editText: EditText = binding.editText
        val viewButton: Button = binding.viewButton
        val visualizationButton: Button = binding.visualizationButton

        maxHeapTree()

        popButton.setOnClickListener {
            if (arr.size > 1) {
                arr.removeAt(arr.size - 1)
                maxHeapTree()
            } else {
                Toast.makeText(this, "삭제할 원소가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        pushButton.setOnClickListener {
            val value = editText.text.toString().toIntOrNull()
            val tree = BinaryTree()
            if (value != null) {
                if (arr.size > 15) {
                    Toast.makeText(this, "더 이상 추가할 수 없습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    arr.add(value)
                    tree.heapify(arr)
                    maxHeapTree()
                }
            } else {
                Toast.makeText(this, "숫자를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        viewButton.setOnClickListener {
            Log.d("MainActivity", "현재 binary heap array: $arr")
        }

        visualizationButton.setOnClickListener {
            if (binaryTreeView.visibility == View.VISIBLE) {
                binaryTreeView.visibility = View.INVISIBLE
                textView.visibility = View.VISIBLE
            } else {
                binaryTreeView.visibility = View.VISIBLE
                textView.visibility = View.INVISIBLE
            }
        }

    }
}
