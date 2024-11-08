import kotlin.math.sqrt

class Point(val x: Double, val y: Double) {
    fun distanceTo(other: Point): Double {
        val dx = other.x - x
        val dy = other.y - y
        return sqrt(dx * dx + dy * dy)
    }
}

fun main() {
    println("Введите количество точек (больше двух):")

    var n = readLine()?.toIntOrNull() ?: 0
    //Проверяется, чтобы `n` было больше двух.
    while(n<=2)
    {
        println("Количество точек должно быть больше двух")
        n = readLine()?.toIntOrNull() ?: 0
    }
    //Считываются координаты каждой точки с консоли, проверяется корректность ввода.
    // Все корректные точки добавляются в список `points`.
    val points = mutableListOf<Point>()
    for (i in 1..n) {
        println("Введите координаты точки $i (x y), разделенные пробелом:")
        val input = readLine() ?: ""
        val coordinates = input.split(" ").map { it.toDoubleOrNull() ?: 0.0 }
        if (coordinates.size != 2) {
            println("Некорректный ввод координат для точки $i. Пропускаем точку.")
            continue
        }
        points.add(Point(coordinates[0], coordinates[1]))
    }

    if (points.size < 2) {
        println("Недостаточно точек для вычисления расстояний. Завершение программы.")
        return
    }
// Инициализируются переменные `minDistance` и `maxDistance` с максимально возможным и минимально возможным значениями соответственно.
    var minDistance = Double.MAX_VALUE
    var maxDistance = Double.MIN_VALUE
//Используются два вложенных цикла для перебора всех пар точек.
    for (i in 0 until points.size - 1) {
        for (j in i + 1 until points.size) {
            //Для каждой пары точек вычисляется расстояние с помощью метода `distanceTo()`.
            val distance = points[i].distanceTo(points[j])
            //Сравниваются найденные расстояния с `minDistance` и `maxDistance` и обновляются значения, если нужно.
            if (distance < minDistance) minDistance = distance
            if (distance > maxDistance) maxDistance = distance
        }
    }

    println("Минимальное расстояние: $minDistance")
    println("Максимальное расстояние: $maxDistance")
}