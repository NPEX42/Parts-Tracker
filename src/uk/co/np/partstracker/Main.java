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

package uk.co.np.partstracker;

import uk.co.np.partstracker.interactors.*;

import java.util.Locale;

public class Main {
    static PartTable partTable = new FlatFilePartsTable();

    static PartsLister lister = new PartsLister();
    static PartsAdder adder = new PartsAdder();
    static PartsRemover remover = new PartsRemover();
    static CountSetter countSetter = new CountSetter();

    static TableLoader loader = new TableLoader();
    static TableSaver saver = new TableSaver();

    public static void main(String[] args) {
        System.out.println("[Debug] Loading Part Table");
        loader.InteractWithTable(partTable, "parts.csv");

        if (args.length > 0) {
            switch (args[0].toLowerCase(Locale.ENGLISH).split(":")[0]) {
                case "-list" -> lister.InteractWithTable(partTable, args[0].split(":"));
                case "-add" -> adder.InteractWithTable(partTable, args[0].split(":"));
                case "-rm" -> remover.InteractWithTable(partTable, args[0].split(":"));
                case "-set-count" -> countSetter.InteractWithTable(partTable, args[0].split(":"));
            }
        } else {
            System.err.println("Usage: PartsTracker [Options]");
            System.err.println("[-list][-add:Name:PartNum:Count][-rm:PartNum][-set-count:partNum:count]");
        }
        saver.InteractWithTable(partTable, "parts.csv");
    }
}
