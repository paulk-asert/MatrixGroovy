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

double[][] encode(String s) { s.bytes*.intValue().collate(3) as double[][] }
String decode(double[] data) { data*.round() as char[] }

def message = 'GROOVY'
def m = new SimpleMatrix(encode(message))
println "Original: $message"

def enKey = new SimpleMatrix([[1, 3], [-1, 2]] as double[][])
def encrypted = enKey.mult(m)
println "Encrypted: ${decode(encrypted.matrix.data)}"

def deKey = enKey.invert()
def decrypted = deKey.mult(encrypted)
println "Decrypted: ${decode(decrypted.matrix.data)}"
