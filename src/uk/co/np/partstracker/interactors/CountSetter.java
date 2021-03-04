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

public class CountSetter implements TableInteractor{
    @Override
    public void InteractWithTable(PartTable table, String... args) {
        PartInfo part = table.get(args[1]);
        part.count = Integer.parseInt(args[2]);
    }
}
