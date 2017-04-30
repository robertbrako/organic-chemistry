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

import java.util.HashMap;
import java.util.Map;

public enum ElementType {

    NULLIUM(0, 0, 0, "  "),
    HYDROGEN(1, 1.0079, 1, "H "), HELIUM(2, 4.0026, 1, "He"),

    LITHIUM(3, 6.941, 2, "Li"), BERYLLIUM(4, 9.0122, 2, "Be"), BORON(5, 10.811, 2, "B "), CARBON(6, 12.011, 2, "C "),
    NITROGEN(7, 14.0067, 2, "N "), OXYGEN(8, 15.9994, 2, "O "), FLUORINE(9, 18.9984, 2, "Fl"), NEON(10, 20.1797, 2, "Ne"),

    SODIUM(11, 22.9898, 3, "Na"), MAGNESIUM(12, 24.3050, 3, "Mg"), ALUMINUM(13, 26.9815, 3, "Al"), SILICON(14, 28.0855, 3, "Si"),
    PHOSPHORUS(15, 30.9738, 3, "P "), SULFUR(16, 32.066, 3, "S "), CHLORINE(17, 35.4527, 3, "Cl"), ARGON(18, 39.948, 3, "Ar"),

    POTASSIUM(19, 39.0983, 4, "K "), CALCIUM(20, 40.078, 4, "Ca"),
    //SC,TI,V,CR,MN,
    IRON(26, 55.847, 4, "Fe"), COBALT(27, 58.9332, 4, "Co"), NICKEL(28, 58.693, 4, "Ni"), COPPER(29, 63.546, 4, "Cu"),
    ZINC(30, 65.39, 4, "Zn"),
    //GA,GE,AS,SE
    BROMINE(35, 79.904, 4, "Br"), KRYPTON(36, 83.80, 4, "Kr")
    ;

    private static final int INITIAL_CAPACITY = 128;
    private final Map<ElementType, Integer> atomicNumbers = new HashMap<>(INITIAL_CAPACITY);
    private final Map<ElementType, Double> massNumbers = new HashMap<>(INITIAL_CAPACITY);
    private final Map<ElementType, Orbital> orbitalDefault = new HashMap<>(INITIAL_CAPACITY);
    private final Map<ElementType, char[]> abbreviations = new HashMap<>(INITIAL_CAPACITY);

    ElementType(int atomicNumber, double atomicMass, int periodNumber, String abbv) {
        atomicNumbers.put(this, atomicNumber);
        massNumbers.put(this, atomicMass);
        orbitalDefault.put(this, new Orbital(atomicNumber));
        abbreviations.put(this, abbv.toCharArray());
    }

    int getAtomicNumber() {
        return atomicNumbers.get(this);
    }

    double getMassNumber() {
        return massNumbers.get(this);
    }

    Orbital getOrbitalReference() {
        return orbitalDefault.get(this);
    }

    char[] getAbbv() {
        return abbreviations.get(this);
    }

    ElementType getByAtomicNumber(int atomicNumber) {
        for (Map.Entry<ElementType, Integer> entry : atomicNumbers.entrySet()) {
            if (entry.getValue().equals(atomicNumber)) {
                return entry.getKey();
            }
        }
        return NULLIUM;
    }
}
