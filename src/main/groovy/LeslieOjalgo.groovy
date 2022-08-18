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

import static org.ojalgo.matrix.Primitive64Matrix.FACTORY as factory

def p0 = factory.column(400, 400, 400)
def L = factory.rows([[0, 2.3, 0.4], [0.6, 0, 0], [0, 0.3, 0]] as double[][])
def p1 = L * p0
println p1
def p2 = L * p1
println p2

def L10 = L ** 10
def p10 = L10 * p0
println p10.toRawCopy1D()*.round()

/*
pwk@QUOKKA:~$ groovysh
Groovy Shell (4.0.4, JVM: 17.0.1)
Type ':help' or ':h' for help.
-----------------------------------------------------------------------------------------------------------------------
groovy:000> :grab org.ojalgo:ojalgo:51.4.0
groovy:000> import static org.ojalgo.matrix.Primitive64Matrix.FACTORY as factory
===> static org.ojalgo.matrix.Primitive64Matrix.FACTORY as factory
groovy:000> p0 = factory.column(400, 400, 400)
===> org.ojalgo.matrix.Primitive64Matrix < 3 x 1 >
{ { 400.0 },
{ 400.0 },
{ 400.0 } }
groovy:000> L = factory.rows([[0, 2.3, 0.4], [0.6, 0, 0], [0, 0.3, 0]] as double[][])
===> org.ojalgo.matrix.Primitive64Matrix < 3 x 3 >
{ { 0.0,        2.3,    0.4 },
{ 0.6,  0.0,    0.0 },
{ 0.0,  0.3,    0.0 } }
groovy:000> p1 = L * p0
===> org.ojalgo.matrix.Primitive64Matrix < 3 x 1 >
{ { 1080.0 },
{ 240.0 },
{ 120.0 } }
groovy:000> p2 = L * p1
===> org.ojalgo.matrix.Primitive64Matrix < 3 x 1 >
{ { 600.0 },
{ 648.0 },
{ 72.0 } }
groovy:000> L10 = L ** 10
===> org.ojalgo.matrix.Primitive64Matrix < 3 x 3 >
{ { 5.064134774399998,  2.1794774975999993,     0.30290319359999995 },
{ 0.4543547903999999,   5.064134774399998,      0.8755682687999998 },
{ 0.6566762015999997,   0.2271773951999999,     0.029617228799999992 } }
groovy:000> p10 = L10 * p0
===> org.ojalgo.matrix.Primitive64Matrix < 3 x 1 >
{ { 3018.606186239999 },
{ 2557.623133439999 },
{ 365.38833023999985 } }
groovy:000> p10.toRawCopy1D()*.round()
===> [3019, 2558, 365]
groovy:000>
 */