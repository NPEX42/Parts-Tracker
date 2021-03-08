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

public class PartsAdder implements TableInteractor {
    @Override
    public void InteractWithTable(PartTable table, String... args) {
        String[] values = args;
        String name = values[1].replace('_', ' ');
        String partNum = values[2];
        int count = Integer.parseInt(values[3]);
        PartInfo info = new PartInfo(count, partNum, name);
        System.out.println("Adding Part '" + PartInfo.SerializeToCSV(info) + "'...");
        table.put(partNum, info);
    }
}
