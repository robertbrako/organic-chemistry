/*
* Copyright 2017 by Robert M. Brako,
* All rights reserved.
*
* Permission is not granted to any person or entity to obtain a copy of this software and associated files
* (the "Software"), to deal in the Software, which includes without limitation any rights to use, copy, modify, merge,
* publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
* furnished to do so.  Permission may only be obtained by a signed and dated license agreement between licensor Robert
* Brako and prospective licensee.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
* WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
* COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
* OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package com.rmbcorp.organicchemistry.elements;

import org.junit.Test;

import static com.rmbcorp.organicchemistry.elements.ElementType.*;
import static org.junit.Assert.assertTrue;

public class OrbitalNodeTest {

    @Test
    public void verifyHydrogenOrbitalsTest() {
        Orbital hydrogenOrbital = HYDROGEN.getOrbitalReference();
        assertTrue(hydrogenOrbital.getLastNodeEnergyType().equals(OrbitalNode.EnergyType.type1S));
    }

    @Test
    public void verifyCarbonOrbitalsTest() {
        Orbital carbonOrbital = CARBON.getOrbitalReference();
        assertTrue(carbonOrbital.getLastNodeEnergyType().equals(OrbitalNode.EnergyType.type2P));
    }

    @Test
    public void verifyZincOrbitalsTest() {
        Orbital zincOrbital = ZINC.getOrbitalReference();
        assertTrue(zincOrbital.getLastNodeEnergyType().equals(OrbitalNode.EnergyType.type3D));
    }
}
