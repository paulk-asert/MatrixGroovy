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

import org.ojalgo.matrix.Primitive64Matrix

double[][] encode(String s) { s.bytes*.intValue().collate(3) as double[][] }

String decode(double[][] data) { data.collect { it*.round() as char[] }.join() }

def factory = Primitive64Matrix.FACTORY
def message = 'GROOVY'
def m = factory.rows(encode(message))
println m

def enKey = factory.rows([[1, 3], [-1, 2]] as double[][])
println enKey

def encrypted = enKey * m
println encrypted
println decode(encrypted.toRawCopy2D())

def deKey = enKey.invert()
println deKey

def decrypted = deKey * encrypted
println decrypted
println decode(decrypted.toRawCopy2D())
