//package example.univ.myapplication
//
//class Hanoi {
//    fun moveTower(height: Int, from: Char, to: Char, aux: Char) {
//        if (height >= 1) {
//            // 가장 위에 있는 원판을 제외한 원판들을 보조 기둥으로 옮김
//            moveTower(height - 1, from, aux, to)
//            // 가장 큰 원판을 목표 기둥으로 옮김
//            println("Move disk $height from $from to $to")
//            // 보조 기둥에 있는 원판들을 목표 기둥으로 옮김
//            moveTower(height - 1, aux, to, from)
//        }
//    }
//
//    fun main() {
//        val hanoi = Hanoi()
//        val numberOfDisks = 3
//        hanoi.moveTower(numberOfDisks, 'A', 'C', 'B')
//    }
//}