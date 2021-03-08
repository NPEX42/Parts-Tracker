/*
 * Copyright (c) 2021.
 *
 *      DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                                                Version 2, December 2004
 *
 *                             Copyright (C) 2004 Sam Hocevar <sam@hocevar.net>
 *
 *                             Everyone is permitted to copy and distribute verbatim or modified
 *                             copies of this license document, and changing it is allowed as long
 *                             as the name is changed.
 *
 *                                        DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                               TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *                              0. You just DO WHAT THE FUCK YOU WANT TO.
 */

package uk.co.np.partstracker.interactors;

import uk.co.np.partstracker.PartInfo;
import uk.co.np.partstracker.PartTable;

public class PartsLister implements TableInteractor {
    @Override
    public void InteractWithTable(PartTable table, String... args) {
        int maxNameSize = 1;
        int maxPNSize = "Part Number".length();
        int maxCountSize = 1;
        for (PartInfo part : table) {
            String name = part.name;
            String partNum = part.partNumber;
            String count = "" + part.count;

            maxNameSize = Math.max(maxNameSize, name.length());
            maxPNSize = Math.max(maxPNSize, partNum.length());
            maxCountSize = Math.max(maxCountSize, count.length());
        }

        System.err.println("Longest Name: " + maxNameSize);
        System.err.println("Longest PartNo.: " + maxPNSize);
        System.err.println("Longest Count: " + maxCountSize);


        String nameStr = String.format("%1$-" + maxNameSize + "s", "name");
        String partNStr = String.format("%1$-" + maxPNSize + "s", "Part Number");
        String countStr = String.format("%1$-" + maxCountSize + "s", "Count");
        System.out.println(nameStr + " | " + partNStr + " | " + countStr);
        System.out.println("+===========================================================+");
        for (PartInfo part : table) {
            String name = String.format("%1$-" + maxNameSize + "s", part.name);
            String partNum = String.format("%1$-" + maxPNSize + "s", part.partNumber);
            String count = String.format("%1$-" + maxCountSize + "s", part.count + "");
            System.out.println(name + " | " + partNum + " | " + count);
        }
    }
}
