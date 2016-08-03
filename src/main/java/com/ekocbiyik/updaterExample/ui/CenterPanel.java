package com.ekocbiyik.updaterExample.ui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * Created by enbiya on 22.06.2016.
 */
public class CenterPanel extends JPanel{

    final static MyLogger logger = new MyLogger(CenterPanel.class);

    private String MLDebugMode = "";


    public CenterPanel(String MLDebugMode){

        this.MLDebugMode = MLDebugMode;
        setLayout(new MigLayout(MLDebugMode + ",gapy 0"));
        setBorder(BorderFactory.createLineBorder(Color.white));


        add(new JLabel("Güncelleme Dosyası Yapılandırılıyor..."), "wrap 1, grow, width 100%");
        add(getProgressBar(), "wrap 4, grow, width 100%");
        add(getLogScreen(), "wrap, grow, width 100%, height 100%");

    }



    private JProgressBar getProgressBar(){

        JProgressBar jBar = new JProgressBar();
        jBar.setEnabled(true);
        jBar.setVisible(true);
        jBar.setIndeterminate(false);

        return jBar;
    }

    private JScrollPane getLogScreen(){

        final MyJTextPane textPane = new MyJTextPane(false);
        textPane.setEditable(false);
        textPane.setStyledDocument(MyLogger.textPaneLogData);

        Font font = new Font(textPane.getFont().getName(), Font.PLAIN, textPane.getFont().getSize());
        textPane.setFont(font);
        textPane.setForeground(Color.black);
        textPane.setBackground(Color.white);

        final JScrollPane logScreen = new JScrollPane(textPane);
        logScreen.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                textPane.select(textPane.getCaretPosition()*logScreen.getFont().getSize(), 1);
            }
        });

        return logScreen;
    }



}
