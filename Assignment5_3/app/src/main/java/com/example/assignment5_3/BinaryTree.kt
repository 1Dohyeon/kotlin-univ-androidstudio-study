package com.example.assignment5_3

class BinaryTree {

    /** 배열을 이진트리로 구성하는 메서드이다.
     * levelOrder 를 통해 트리를 구성한다. */
    fun createBinaryTree(arr: Array<Int>): Node? {
        if (arr.isEmpty()) return null
        return levelOrder(arr, null, 1)
    }

    /** 배열을 트리로 구성하기 위한 메서드이다.
     * 루트 노드를 설정하고, 레벨 순회 방식으로 왼쪽-오른쪽 재귀호출을 통해 트리를 구성한다. */
    private fun levelOrder(arr: Array<Int>, root: Node?, i: Int): Node? {
        var root = root
        if (i < arr.size) {
            val temp = Node(arr[i])
            root = temp

            val leftIndex = 2 * i // 왼쪽 자식
            val rightIndex = 2 * i + 1 // 오른쪽 자식

            if (leftIndex < arr.size) {
                root.left = levelOrder(arr, root.left, leftIndex)
            }
            if (rightIndex < arr.size) {
                root.right = levelOrder(arr, root.right, rightIndex)
            }
        }
        return root
    }

    /** downHeap을 통해서 힙을 구성하는 메서드이다.
     * down heap의 성질과는 달리 바텀업 방식으로 올라간다.
     * 중간 이후는 리프노드이기에 리프노드는 자식이 없으므로 중간부터 시작한다.
     * 리프노드를 제외한 모든 노드에 대하여 down heap을 하기에 모든 서브트리로부터
     * 큰 값이 부모 노드에 오도록 할 수 있다. */
    fun heapify(arr: ArrayList<Int>) {
        val n = arr.size
        for (i in n / 2 - 1 downTo 1) {
            downHeap(arr, n, i)
        }
    }

    /** down heap이란  최대힙 속성을 유지하기 위해 현재 노드와
     * 자식 노드(왼쪽->오른쪽 순서)를 비교하며 자식이 크다면 부모와 위치를 바꾼 후,
     * 바꾼 자식으로 이동하여 재귀호출을 통해 계속 크기를 비교하는 메서드이다.
     * 위에서 아래로 내려가는 방식이기에 down heap이라고 한다. */
    private fun downHeap(arr: ArrayList<Int>, n: Int, i: Int) {
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

            downHeap(arr, n, largest)
        }
    }
}
