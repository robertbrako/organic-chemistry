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

public class ElementTest {

    private static final boolean DEBUG = false;

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

    @Test
    public void weCanCreateMethaneTest() {
        Element carbon = new Element(CARBON);
        for (int i = 0; i  < 4; i++) {
            Element.bond(carbon, new Element(HYDROGEN));
        }
        assertEquals(4, carbon.totalBondCount());
        assertFalse(carbon.canBond());
    }

    @Test
    public void graphCarbonChainWithNitrogenTest() {
        Element carbon1 = prepareCompound(CARBON, 3);
        Element carbon2 = prepareCompound(CARBON, 2);
        Element carbon3 = prepareCompound(CARBON, 2);
        Element carbon4 = prepareCompound(CARBON, 2);
        Element nitro = prepareCompound(NITROGEN, 2);

        Element.bond(carbon3, carbon1);
        Element.bond(carbon4, carbon3);
        Element.bond(carbon4, carbon2);
        Element.bond(nitro, carbon2);

        assertFalse(carbon1.canBond());
        assertFalse(carbon2.canBond());
        assertFalse(carbon3.canBond());
        assertFalse(carbon4.canBond());
        assertFalse(nitro.canBond());
        graph(carbon3);
    }

    @Test
    public void graphBasicBranchedChainTest() {
        Element carbon1 = prepareCompound(CARBON, 2);
        Element carbon2 = prepareCompound(CARBON, 2);
        Element carbon3 = prepareCompound(CARBON, 1);
        Element carbon4 = prepareCompound(CARBON, 2);
        Element carbon5 = prepareCompound(CARBON, 3);
        Element carbon6 = prepareCompound(CARBON, 2);
        Element carbon7 = prepareCompound(CARBON, 3);

        Element.bond(carbon1, carbon2);
        Element.bond(carbon2, carbon3);
        Element.bond(carbon3, carbon4);
        Element.bond(carbon4, carbon5);
        //order is currently important in order to get [H C C...] instead of [C H C...]; needs refinement
        Element.bond(carbon3, carbon6);
        Element.bond(carbon6, carbon7);

        assertTrue(carbon1.canBond());
        assertFalse(carbon2.canBond());
        assertFalse(carbon3.canBond());
        assertFalse(carbon4.canBond());
        assertFalse(carbon5.canBond());
        assertFalse(carbon6.canBond());
        assertFalse(carbon7.canBond());

        graph(carbon1);
    }

    private void graph(Element element) {
        if (!DEBUG) return;
        for (char[] line : Element.graph(element)) {
            System.out.println(line);
        }
    }

    private Element prepareCompound(ElementType type, int hCount) {
        Element element = new Element(type);
        for (int i = 0; i  < hCount; i++) {
            Element.bond(element, new Element(HYDROGEN));
        }
        return element;
    }
}
