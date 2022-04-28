package lab4

import jetbrains.datalore.base.values.Color
import jetbrains.letsPlot.geom.geomLine
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.letsPlot

class Painter {
    private var p = letsPlot()

    fun addLineFunction(
        function: Map<String, Any>,
        color: Color = Color.BLUE,
        alpha: Double = 0.5
    ) {
        p += geomLine(data = function, color = color, alpha = alpha, showLegend = true) {
            this.x = "x"; this.y = "y"; this.color = "name"
        }
    }

    fun addScatterFunction(
        function: Map<String, Any>,
        color: Color = Color.BLUE,
        alpha: Double = 0.5
    ) {
        p += geomPoint(data = function, color = color, alpha = alpha, showLegend = true) {
            this.x = "x"; this.y = "y"
        }
    }

    fun draw() {
        p + ggsize(500, 250)
        p.show()
    }
}