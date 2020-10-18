package com.codygill;

import javax.swing.*;
import javax.swing.plaf.metal.*;
import java.io.*;
import java.awt.event.*;

public class TextEditor extends JFrame implements ActionListener {

    JTextArea text;
    JFrame frame;

    TextEditor() {

        frame = new JFrame("Text Editor");

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                InstantiationException | IllegalAccessException e) {
            //handle
        }

        text = new JTextArea();

        // Here we are creating the menu bar and options
        JMenuBar menuBar = new JMenuBar();

        JMenu menu1 = new JMenu("File");
        JMenuItem menuItem1 = new JMenuItem("New");
        JMenuItem menuItem2 = new JMenuItem("Open");
        JMenuItem menuItem3 = new JMenuItem("Save");
        JMenuItem menuItem4 = new JMenuItem("Print");

        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);
        menuItem4.addActionListener(this);

        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menu1.add(menuItem3);
        menu1.add(menuItem4);

        JMenu menu2 = new JMenu("Edit");
        JMenuItem menuItem5 = new JMenuItem("Cut");
        JMenuItem menuItem6 = new JMenuItem("Copy");
        JMenuItem menuItem7 = new JMenuItem("Paste");

        menu2.add(menuItem5);
        menu2.add(menuItem6);
        menu2.add(menuItem7);

        JMenuItem menuClose = new JMenuItem("Close");
        menuClose.addActionListener(this);

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menuClose);

        frame.setJMenuBar(menuBar);
        frame.add(text);
        frame.setSize(1000, 1000); //change to default to user monitor size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void action(ActionEvent e) {

        String string = e.getActionCommand();

        if(string.equals("Cut")) {
            text.cut();
        }
        else if(string.equals("Copy")) {
            text.copy();
        }
        else if(string.equals("Paste")) {
            text.paste();
        }
        else if(string.equals("Save")) {
            JFileChooser fileChooser = new JFileChooser("f:");
            int saveDialogue = fileChooser.showSaveDialog(null);

            if (saveDialogue == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try (PrintWriter out = new PrintWriter(new FileWriter("TestFile.txt"))){
                    text.write(out);
                    //FileWriter writer = new FileWriter(file, false);
                    //BufferedWriter buffWriter = new BufferedWriter(writer);
                    //buffWriter.write(text.getText());
                    //buffWriter.flush();
                    //buffWriter.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(frame, evt.getMessage());
                }
            }
            else {
                JOptionPane.showMessageDialog(frame, "Cancelled");
            }
        }
        else if(string.equals("Print")) {
            try {
                text.print();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(frame, evt.getMessage());
            }
        }
        else if(string.equals("Open")) {
            JFileChooser fileChooser = new JFileChooser("f:");
            int openDialogue = fileChooser.showOpenDialog(null);

            if(openDialogue == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    String string2;
                    StringBuilder stringBuilder = new StringBuilder();
                    FileReader fileRead = new FileReader(file);
                    BufferedReader buffRead = new BufferedReader(fileRead);
                    stringBuilder = new StringBuilder(buffRead.readLine());

                    while((string2 = buffRead.readLine()) != null) {
                        stringBuilder.append("\n").append(string2);
                    }

                    text.setText(stringBuilder.toString());
                } catch (Exception evt){
                    JOptionPane.showMessageDialog(frame, evt.getMessage());
                }
            }
            else {
                JOptionPane.showMessageDialog(frame, "Cancelled");
            }
        }
        else if(string.equals("New")) {
            text.setText("");
        }
        else if (string.equals("Close")) {
            frame.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
