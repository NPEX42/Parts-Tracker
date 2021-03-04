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

import java.io.*;

public class FlatFilePartsTable extends PartTable {

    @Override
    public void SaveTable(String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for(PartInfo part : this) {
                String info = PartInfo.SerializeToCSV(part);
                writer.write(info+System.lineSeparator());
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void LoadTable(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while((line = reader.readLine()) != null) {
                try {
                    PartInfo part = PartInfo.DeserializeFromCSV(line);
                    put(part.partNumber, part);
                } catch (RuntimeException rtex) {
                    rtex.printStackTrace();
                }
            }
            reader.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }
}
