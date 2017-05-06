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

/** this is a bit wonky, since instead of designing an Electron class, I'm letting the orbital handle the electron functionality **/
class OrbitalNode {

    private static long electronCounter = 0;

    private EnergyType energyType;
    private long[] electronIds;
    private int[] electrons;
    private int lastIndex;
    private int firstBondIndex;

    OrbitalNode(EnergyType energyType) {
        this.energyType = energyType;
        electrons = new int[energyType.capacity];
        electronIds = new long[energyType.capacity];
    }

    OrbitalNode(OrbitalNode lastNode) {
        this(lastNode.getEnergyType());
        lastIndex = lastNode.lastIndex;
    }

    EnergyType getEnergyType() {
        return energyType;
    }

    int fill(int count) {
        int i = 0;
        while (i < electrons.length && i < count) {
            electrons[i] = 1 - 2*(2*i/electrons.length); // gives either 1 or -1
            electronIds[i] = electronCounter++;
            i++;
        }
        lastIndex = i;
        return i;
    }

    boolean canBond() {
        return lastIndex < electrons.length;
    }

    boolean bond(OrbitalNode otherOrbitalNode) {
        if (canBond() && otherOrbitalNode.canBond()) {//for now, we add reference without modifying electron[index] value from 0 to +/-1
            electronIds[lastIndex] = otherOrbitalNode.electronIds[otherOrbitalNode.firstBondIndex];
            lastIndex++;
            otherOrbitalNode.firstBondIndex++;

            otherOrbitalNode.electronIds[otherOrbitalNode.lastIndex] = electronIds[firstBondIndex];
            otherOrbitalNode.lastIndex++;
            firstBondIndex++;
            return true;
        }
        return false;
    }

    enum EnergyType {
        type1S(2), type2S(2), type2P(6), type3S(2), type3P(6), type4S(2), type3D(10), ENERGY_TYPE_NONE(0);


        final int capacity;

        EnergyType(int capacity) {
            this.capacity = capacity;
        }

        EnergyType next() {
            if (this.equals(ENERGY_TYPE_NONE)) {
                return ENERGY_TYPE_NONE;
            }
            return values()[ordinal() + 1];
        }
    }
}
