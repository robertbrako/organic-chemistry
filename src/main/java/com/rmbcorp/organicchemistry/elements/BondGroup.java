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

class BondGroup {

    private Element[] bonds;
    private int bondCount = 0;

    BondGroup(int size) {
        bonds = new Element[size];
    }

    int add(Element element) {
        int returnIndex = -1;
        int complement = -1;
        for (int i = 0; i < bonds.length; i++) {
            if (bonds[i] == null) {
                bonds[i] = element;
                complement = (i + 2) % 4;
                bondCount++;
                returnIndex = i;
                break;
            }
        }
        rearrangeBond(element, complement);
        return returnIndex;
    }

    private void rearrangeBond(Element element, int complement) {
        Element opposite = bonds[complement];
        if (opposite != null && element.electroNegativity() > opposite.electroNegativity()) {
            for (int i = 0; i < bonds.length; i++) {
                if (bonds[i] == null) {
                    bonds[i] = opposite;
                    bonds[complement] = null;
                }
            }
        }
    }

    int add(Element element, int oldIndex) {
        Element toPop;
        int complement = (oldIndex + 2) % 4;
        if (bonds[complement] != null) {
            toPop = bonds[complement];
            int nextOpenSlot = 0;
            while(bonds[nextOpenSlot] != null) {
                nextOpenSlot++;
            }
            bonds[nextOpenSlot] = toPop;
        }
        bonds[complement] = element;
        bondCount++;
        rearrangeBond(element, oldIndex);
        return oldIndex;
    }

    int bondCount() {
        return bondCount;
    }

    int bondCountWith(Element element) {
        int counter = 0;
        for (Element existing : bonds) {
            if (element.equals(existing)) {
                counter++;
            }
        }
        return counter;
    }

    Element get(int index) {
        return bonds[index];
    }
}
