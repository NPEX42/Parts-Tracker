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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class PartTable implements Iterable<PartInfo> {

    private final Map<String, PartInfo> table = new HashMap<>();

    public PartInfo get(Object key) {
        return table.get(key);
    }

    public boolean containsKey(Object key) {
        return table.containsKey(key);
    }

    public PartInfo put(String key, PartInfo value) {
        return table.put(key, value);
    }

    public void remove(String partNum) {
        table.remove(partNum);
    }

    @Override
    public Iterator<PartInfo> iterator() {
        return table.values().iterator();
    }

    public abstract void LoadTable(String path);

    public abstract void SaveTable(String path);

    public PartInfo GetByName(String name) throws PartNotFoundException {
        for(PartInfo part : this)  {
            if(name.equals(part.name)) return part;
        }
        throw new PartNotFoundException();
    }

    public static class PartNotFoundException extends RuntimeException {}

}
