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

package uk.co.np.partstracker.swing;

import uk.co.np.partstracker.FlatFilePartsTable;
import uk.co.np.partstracker.PartInfo;
import uk.co.np.partstracker.PartTable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class MainWindow {
    private JPanel pnlMain;
    private JList<String> partsList;
    private JMenuBar menuBar;
    private PartTable partTable;
    private PartInfo activePart;
    private File activeFile;
    private JFrame frame;

    private void createUIComponents() {
        pnlMain = new JPanel();
        menuBar = new JMenuBar();
        partsList = new JList<>();

        partTable = new FlatFilePartsTable();

        menuBar = new JMenuBar();

        frame.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenu = new JMenuItem("Save");
        JMenuItem saveAsMenu = new JMenuItem("Save As");
        JMenuItem loadMenu = new JMenuItem("Load");
        JMenuItem exitMenu = new JMenuItem("Exit");

        fileMenu.add(loadMenu);
        fileMenu.add(saveMenu);
        fileMenu.add(saveAsMenu);
        fileMenu.add(exitMenu);

        menuBar.add(fileMenu);

        saveMenu.addActionListener(this::OnSave);
        saveAsMenu.addActionListener(this::OnSaveAs);
        exitMenu.addActionListener((event) -> {
            frame.dispose();
            OnSave(event);
        });
        loadMenu.addActionListener(this::OnLoad);

        partsList.addListSelectionListener(this::onListItemSelected);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Parts Tracker V0.2.0a");
        frame.setContentPane(new MainWindow(frame).pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 720);
        frame.setVisible(true);
    }

    public MainWindow(JFrame _frame) {
        this.frame = _frame;
        $$$setupUI$$$();
        partsList = new JList<>();
        UpdatePartsList();
    }

    public void UpdatePartsList() {
        DefaultListModel<String> parts = new DefaultListModel<>();

        for (PartInfo part : partTable) {
            parts.addElement(part.partNumber);
        }
        partsList = new JList<>(parts);
    }

    public void LoadPartsTable(File file) {
        partTable.LoadTable(file.getAbsolutePath());
        UpdatePartsList();
    }

    public void SavePartsTableAs(File file) {
        activeFile = file;
        partTable.SaveTable(file.getAbsolutePath());
    }

    public void SavePartsTable() {
        if (activeFile != null) {
            partTable.SaveTable(activeFile.getAbsolutePath());
        } else {
            JFileChooser jfc = new JFileChooser();
            jfc.showOpenDialog(null);
            File file = jfc.getSelectedFile();
            if (file != null) {
                partTable.SaveTable(file.getAbsolutePath());
            }
        }
    }

    public void AddPart(PartInfo part) {
        UpdatePartsList();
    }

    public void RemovePart(PartInfo part) {
        UpdatePartsList();
    }

    public void onListItemSelected(ListSelectionEvent event) {
        if (partsList.getSelectedIndex() != -1) {
            UpdateActivePart(partTable.get(partsList.getSelectedValue()));
        }
    }

    public void OnLoad(ActionEvent event) {
        System.err.println("Loading Part Table...");
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(null);
        File file = jfc.getSelectedFile();
        if (file != null) {
            activeFile = file;
            LoadPartsTable(file);
        }
    }

    public void OnSave(ActionEvent event) {
        SavePartsTable();
    }

    public void OnSaveAs(ActionEvent event) {
        System.err.println("Saving Part Table...");
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(null);
        File file = jfc.getSelectedFile();
        if (file != null) {
            partTable.SaveTable(file.getAbsolutePath());
        }
    }

    public void UpdateActivePart(PartInfo info) {
        activePart = info;
        System.err.println(info);
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        pnlMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        pnlMain.add(partsList, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }

}
