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

import static com.rmbcorp.organicchemistry.elements.ElementType.CARBON;
import static com.rmbcorp.organicchemistry.elements.ElementType.HYDROGEN;
import static com.rmbcorp.organicchemistry.elements.ElementType.NITROGEN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ElementTest {

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
    public void graphTest() {
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
        char[][] graph = Element.graph(carbon3);
        for (char[] line : graph) {
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
