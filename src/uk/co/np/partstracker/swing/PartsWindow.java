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

import uk.co.np.partstracker.FlatFilePartsTable;
import uk.co.np.partstracker.PartInfo;
import uk.co.np.partstracker.PartTable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class PartsWindow extends JFrame {
    //Menu Items
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem exitMenu;
    JMenuItem loadMenu;

    ThreadedActionEventListener loadListener = new ThreadedActionEventListener(this::OnLoadFile);

    JList<String> partsList;
    PartEditorComponent editor = new PartEditorComponent();

    PartTable partTable = new FlatFilePartsTable();

    public static void main(String[] args) {
        new PartsWindow(1080, 720);
    }


    public PartsWindow(int width, int height) {
        SetupUI();
        setSize(width, height);
        setVisible(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                OnClose();
            }
        });
    }

    private void SetupUI() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        exitMenu = new JMenuItem("Exit");
        loadMenu = new JMenuItem("Load");
        loadMenu.addActionListener(loadListener.GetListener());
        exitMenu.addActionListener(this::OnExitMenu);

        menuBar.add(fileMenu);
        fileMenu.add(loadMenu);
        fileMenu.add(exitMenu);

        setJMenuBar(menuBar);

        add(editor, BorderLayout.CENTER);
        UpdatePartsList();
    }

    private void UpdatePartsList() {
        Vector<String> list = new Vector<>();
        for(PartInfo part : partTable) {
            list.add(part.name);
        }
        RebuildPartsList(list);
    }

    private void RebuildPartsList(Vector<? extends String> list) {
        partsList = new JList<>(list);
        partsList.addListSelectionListener(this::OnListItemSelected);
        add(partsList, BorderLayout.WEST);
    }

    private void OnClose() {
        dispose();
        System.exit(0);
    }

    private void OnExitMenu(ActionEvent event) {
        OnClose();
    }

    private void OnListItemSelected(ListSelectionEvent event) {
        String partName = partsList.getSelectedValue();
        if(partName != null) {
            DisplayPartEditor(partName);
        }
    }

    private void DisplayPartEditor(String partName) {
        PartInfo part = partTable.GetByName(partName);
        editor.SetActivePart(part);
    }

    private void OnLoadFile(ActionEvent event) {
        System.err.println("Hello Loading");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("Goodbye Loading");
    }


}
