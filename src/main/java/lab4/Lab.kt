package lab4

import jetbrains.datalore.base.values.Color
import lab1.utils.Input
import lab3.Main

val input = Input()

fun main() {
    val func = Main.selectEquation(Lab4Functions().javaClass)
    print("Enter left bound: ")
    val start = input.readDouble()
    print("Enter right bound: ")
    val end = input.readDouble()

    val size = 20

    val step = (end - start) / size

    val xFunc = DoubleArray(((end - start) * 100).toInt()) { start + it * (0.01) }
    val xDot = DoubleArray(size) { start + step * it }
    val y = DoubleArray(xFunc.size) { func.apply(xFunc[it]) }
    val yNoise = DoubleArray(xDot.size) { func.apply(xDot[it]) }

    val interpolator = Interpolator()
    val interpolationArray = xDot.clone().map { it + step / 2 }.toDoubleArray()
    val results =
        DoubleArray(xDot.size) { interpolator.interpolate(interpolationArray[it], xDot, yNoise) }

    val points = mapOf("x" to xDot, "y" to yNoise, "name" to List(xDot.size) { "initial" })
    val initialFunc = mapOf("x" to xFunc, "y" to y)
    val result = mapOf("x" to interpolationArray, "y" to results)

    val painter = Painter()
    painter.addLineFunction(initialFunc, Color.GREEN)
    painter.addScatterFunction(points, Color.BLUE, 1.0)
    painter.addScatterFunction(result, Color.RED, 1.0)
    painter.draw()
}