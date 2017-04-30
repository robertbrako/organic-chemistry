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

import java.util.*;

class Orbital {

    private final Map<OrbitalNode.EnergyType, OrbitalNode> orbitalNodeMap;
    private OrbitalNode lastNode;

    Orbital(int electrons) {
        orbitalNodeMap = initialCreate(electrons);
    }

    Orbital(Orbital primitive) {
        orbitalNodeMap = new HashMap<>(primitive.orbitalNodeMap);
        lastNode = new OrbitalNode(primitive.getLastNode());
    }

    private Map<OrbitalNode.EnergyType, OrbitalNode> initialCreate(int electronCount) {
        Map<OrbitalNode.EnergyType, OrbitalNode> result = new EnumMap<>(OrbitalNode.EnergyType.class);
        int processed = 0;
        OrbitalNode.EnergyType energyType = OrbitalNode.EnergyType.type1S;
        OrbitalNode node;
        while (electronCount > processed  && !energyType.equals(OrbitalNode.EnergyType.ENERGY_TYPE_NONE)) {
            node = new OrbitalNode(energyType);
            processed += node.fill(electronCount - processed);
            result.put(energyType, node);
            lastNode = node;
            energyType = energyType.next();
        }
        return result;
    }

    private OrbitalNode getLastNode() {
        return lastNode;
    }

    OrbitalNode.EnergyType getLastNodeEnergyType() {
        return lastNode.getEnergyType();
    }

    boolean bond(Orbital otherOrbital) {
        return lastNode.bond(otherOrbital.getLastNode());
    }

    boolean canBond() {
        return lastNode.canBond();
    }
}
