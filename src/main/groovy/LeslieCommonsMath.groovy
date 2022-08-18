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

import org.apache.commons.math3.linear.MatrixUtils

double[] init = [400,   // 0..<3
                 400,   // 3..<6
                 400]   // 6..9
def p0 = MatrixUtils.createRealVector(init)
println "Initial populations: $p0"

double[][] data = [
        [0, 2.3, 0.4],  // B1 B2 B3
        [0.6, 0, 0],    // S1  0  0
        [0, 0.3, 0]     //  0 S1  0
]
def L = MatrixUtils.createRealMatrix(data)
def p1 = L.operate(p0)
println "Population after 1 round: $p1"

def p2 = L.operate(p1)
println "Population after 2 rounds: $p2"

def L10 = L ** 10
println "Population after 10 rounds: ${L10.operate(p0).toArray()*.round()}"
