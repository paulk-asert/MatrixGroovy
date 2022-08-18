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

import org.ejml.simple.SimpleMatrix
import benchmark.MatrixMultiplication

def m = new SimpleMatrix([[1, 1], [1, 0]] as double[][])
double[] expected = [3, 2, 2, 1]
def step1, result

long t0 = System.nanoTime()
1000.times {
    step1 = new SimpleMatrix(2, 2)
    result = new SimpleMatrix(2, 2)
    MatrixMultiplication.mult_ikj_simple(m.matrix, m.matrix, step1.matrix)
    MatrixMultiplication.mult_ikj_simple(step1.matrix, m.matrix, result.matrix)
    assert result.matrix.data == expected
}

long t1 = System.nanoTime()
1000.times {
    step1 = new SimpleMatrix(2, 2)
    result = new SimpleMatrix(2, 2)
    MatrixMultiplication.mult_ikj(m.matrix, m.matrix, step1.matrix)
    MatrixMultiplication.mult_ikj(step1.matrix, m.matrix, result.matrix)
    assert result.matrix.data == expected
}

long t2 = System.nanoTime()
1000.times {
    step1 = new SimpleMatrix(2, 2)
    result = new SimpleMatrix(2, 2)
    MatrixMultiplication.mult_ikj_vector(m.matrix, m.matrix, step1.matrix)
    MatrixMultiplication.mult_ikj_vector(step1.matrix, m.matrix, result.matrix)
    assert result.matrix.data == expected
}

long t3 = System.nanoTime()
printf "Simple:    %6.2f ms\n", (t1 - t0)/1000_000
printf "Optimized: %6.2f ms\n", (t2 - t1)/1000_000
printf "Vector:    %6.2f ms\n", (t3 - t2)/1000_000
