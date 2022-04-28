package lab4

class Interpolator {

    private fun buildTable(y: DoubleArray): Array<DoubleArray> {
        val table = Array(y.size) { DoubleArray(y.size) }
        table[0] = y
        for (i in 1 until y.size) {
            for (j in 0 until y.size - i) {
                table[i][j] = table[i - 1][j + 1] - table[i - 1][j]
            }
        }
        return table
    }

    private fun fact(x: Int): Int {
        var res = 1
        for (i in 1..x) {
            res *= i
        }
        return res
    }

    private fun Newton(value: Double, x: DoubleArray, o: Int, diffs: Array<DoubleArray>): Double {
        var result = diffs[0][o]
        val t = (value - x[o]) / (x[1] - x[0])
        for (i in 1..o) {
            var temp = diffs[i][o]
            if (temp.isNaN() || temp.isInfinite()) {
                continue
            }
            for (j in 0 until i) {
                temp *= (t - j)
            }
            temp /= fact(i)
            result += temp
        }
        return result
    }

    fun interpolate(value: Double, x: DoubleArray, y: DoubleArray): Double {
        val table = buildTable(y)
        var index: Int? = null
        for (i in x.indices) {
            if (x[i] > value) {
                index = i
                break
            } else if (x[i] == value) {
                return y[i]
            }
        }
        return when (index) {
            null -> {
                Newton(value, x, x.size - 1, table)
            }
            0 -> {
                Newton(value, x, 0, table)
            }
            else -> {
                if (index < x.size / 2) {
                    Newton(value, x, index, table)
                } else {
                    Newton(value, x, index, table)
                }
            }
        }
    }

}