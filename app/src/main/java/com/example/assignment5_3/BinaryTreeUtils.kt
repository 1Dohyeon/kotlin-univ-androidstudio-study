package com.example.assignment5_3

class BinaryTree {

    fun createBinaryTree(arr: Array<Int>): Node? {
        if (arr.isEmpty()) return null
        return insertLevelOrder(arr, null, 1)
    }

    private fun insertLevelOrder(arr: Array<Int>, root: Node?, i: Int): Node? {
        var root = root
        if (i < arr.size) {
            val temp = Node(arr[i])
            root = temp

            // Adjusting indices to match 1-based array
            val leftIndex = 2 * i
            val rightIndex = 2 * i + 1

            if (leftIndex < arr.size) {
                root.left = insertLevelOrder(arr, root.left, leftIndex)
            }
            if (rightIndex < arr.size) {
                root.right = insertLevelOrder(arr, root.right, rightIndex)
            }
        }
        return root
    }

    fun heapify(arr: ArrayList<Int>) {
        val n = arr.size
        for (i in n / 2 - 1 downTo 1) {
            heapifyDown(arr, n, i)
        }
    }

    private fun heapifyDown(arr: ArrayList<Int>, n: Int, i: Int) {
        var largest = i
        val left = 2 * i
        val right = 2 * i + 1

        if (left < n && arr[left] > arr[largest]) {
            largest = left
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right
        }

        if (largest != i) {
            val swap = arr[i]
            arr[i] = arr[largest]
            arr[largest] = swap

            heapifyDown(arr, n, largest)
        }
    }
}
