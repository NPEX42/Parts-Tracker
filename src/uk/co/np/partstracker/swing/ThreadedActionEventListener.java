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

package uk.co.np.partstracker.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadedActionEventListener {
    private final ActionListener listener;
    public ThreadedActionEventListener(ActionListener listener) {
        this.listener = listener;
    }

    public ActionListener GetListener() {return this::actionPerformed;}
    public void actionPerformed(ActionEvent e) {
        new Thread(() -> {listener.actionPerformed(e);}).start();
    }
}
