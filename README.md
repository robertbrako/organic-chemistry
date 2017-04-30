# organic-chemistry
[![Build Status](https://travis-ci.org/robertbrako/organic-chemistry.svg?branch=master)](https://travis-ci.org/robertbrako/organic-chemistry)
<br>
Converting my organic chemistry textbook into a java program has been a lot of fun!

Sample input:

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

Sample Output:

        H N H
        H C H
        H C H
        H C H
        H C H
          H

Perhaps someday I'll see if it can handle Adenine, Thymine, Cytosine, and Guanine...