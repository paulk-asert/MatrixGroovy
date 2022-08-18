/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import groovy.swing.SwingBuilder
import org.ojalgo.matrix.Primitive64Matrix

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Component
import java.awt.Graphics
import java.awt.Polygon

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE
import static org.ojalgo.matrix.Primitive64Matrix.FACTORY as factory

new SwingBuilder().edt {
    def frame = frame(title: 'Shapes', size: [420, 440], show: true, defaultCloseOperation: DISPOSE_ON_CLOSE) {
        //contentPane.background = Color.WHITE
        widget(new CustomPaintComponent())
    }
    frame.contentPane.background = Color.WHITE
}

class CustomPaintComponent extends Component {
    static final Color violet = new Color(0x67, 0x27, 0x7A, 127)
    static final Color seaGreen = new Color(0x69, 0xCC, 0x67, 127)
    static final Color crystalBlue = new Color(0x06, 0x4B, 0x93, 127)
    static drawPolygon(Graphics g, List pts, boolean fill) {
        def poly = new Polygon().tap {
            pts.each {
                addPoint(*it.toRawCopy1D()*.round()*.intValue().collect { it + 200 })
            }
        }
        fill ? g.fillPolygon(poly) : g.drawPolygon(poly)
    }

    static List<Primitive64Matrix> vectors(List<Integer>... pts) {
        pts.collect{ factory.column(*it) }
    }

    static transform(List<Number>... lists) {
        factory.rows(lists as double[][])
    }

    void paint(Graphics g) {
        g.drawLine(0, 200, 400, 200)
        g.drawLine(200, 0, 200, 400)
        g.stroke = new BasicStroke(2)

        def triangle = vectors([-85, -150], [-145, -30], [-25, -30])
        g.color = seaGreen
        drawPolygon(g, triangle, true)
        // transform triangle
        def rot90 = transform([0, 1], [-1, 0])
        def bigger = transform([1.25, 0], [0, 1.25])
        def smaller = transform([0.75, 0], [0, 0.75])
        def triangle_ = triangle.collect{ rot90 * bigger * it }
        drawPolygon(g, triangle_, false)
        triangle_ = triangle.collect{ rot90 * smaller * it }
        drawPolygon(g, triangle_, false)

        def rectangle = vectors([0, -110], [0, -45], [95, -45], [95, -110])
        g.color = crystalBlue
        drawPolygon(g, rectangle, true)
        // transform rectangle
        def flipV = transform([1, 0], [0, -1])
        def rectangle_ = rectangle.collect{ flipV * it }
        drawPolygon(g, rectangle_, false)
        def flipH_shear = transform([-1, 0.5], [0, 1])
        rectangle_ = rectangle.collect{ flipH_shear * it }
        drawPolygon(g, rectangle_, false)

        def trapezoid = vectors([50, 50], [70, 100], [100, 100], [120, 50])
        g.color = violet
        drawPolygon(g, trapezoid, true)
        // transform trapezoid

        def rot45 = transform([0.707, 0.707], [-0.707, 0.707])
        def trapezoid_
        (1..6).each { z ->
            trapezoid_ = trapezoid.collect{ rot45 ** z * it }
            drawPolygon(g, trapezoid_, false)
        }

    }
}
