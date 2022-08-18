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

import org.nd4j.linalg.factory.Nd4j
import org.nd4j.linalg.inverse.InvertMatrix

double[][] encode(String s) { s.bytes*.intValue().collate(3) as double[][] }

String decode(double[][] data) { data.collect { it*.round() as char[] }.join() }

def message = 'GROOVY'
def m = Nd4j.create(encode(message))
println m

def enKey = Nd4j.create([[1, 3], [-1, 2]] as double[][])
println enKey

def encrypted = enKey.mmul(m)
println encrypted
println decode(encrypted.toDoubleMatrix())

def deKey = InvertMatrix.invert(enKey, false)
println deKey

def decrypted = deKey.mmul(encrypted)
println decrypted
println decode(decrypted.toDoubleMatrix())
