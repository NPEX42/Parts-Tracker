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

import uk.co.np.partstracker.PartInfo;

import javax.swing.*;

public class PartEditorComponent extends JPanel {
    JTextField partNameField, partNumberField;
    JSpinner partCountSpinner;

    PartInfo currentPart;

    PartEditorComponent() {
        partNameField = new JTextField();
        partNumberField = new JTextField();
        partCountSpinner = new JSpinner(new SpinnerNumberModel(1,0,99,1));



        SetupUI();
    }

    private void SetupUI() {
    }

    public void SetActivePart(PartInfo part) {
        currentPart = part;

        partCountSpinner.setValue(part.count);
        partNameField.setText(part.name);
        partNumberField.setText(part.partNumber);
    }


}
