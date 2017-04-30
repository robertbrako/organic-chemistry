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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

    @Test
    public void hydrogenBondTest() {
        Element firstH = new Element(HYDROGEN);
        Element secondH = new Element(HYDROGEN);
        assertTrue(firstH.bondCountWith(secondH) == 0);
        assertTrue(firstH.canBond());
        assertTrue(secondH.canBond());

        Element.bond(firstH, secondH);
        assertTrue(firstH.bondCountWith(secondH) == 1);
        assertFalse(firstH.canBond());
        assertFalse(secondH.canBond());

        Element.bond(firstH, secondH);
        assertTrue(firstH.bondCountWith(secondH) == 1);
    }

    @Test
    public void primitivesUnaffectedByInstancesTest() {
        OrbitalNode.EnergyType carbonLastNode = CARBON.getOrbitalReference().getLastNodeEnergyType();
        Element hydrogen = new Element(HYDROGEN);
        Element carbon = new Element(CARBON);
        Element.bond(hydrogen, carbon);
        assertTrue(hydrogen.bondCountWith(carbon) == 1);

        Element newHydrogen = new Element(HYDROGEN);
        assertTrue(newHydrogen.bondCountWith(carbon) == 0);
        assertTrue(newHydrogen.canBond());
        assertTrue(HYDROGEN.getOrbitalReference().canBond());
        assertTrue(carbonLastNode.equals(CARBON.getOrbitalReference().getLastNodeEnergyType()));
    }
}
