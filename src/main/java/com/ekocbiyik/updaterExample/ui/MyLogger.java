package com.ekocbiyik.updaterExample.ui;


import org.apache.log4j.Logger;

import javax.swing.text.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by enbiya on 15.04.2016.
 */
public class MyLogger {

    private static Logger myLogger;
    public static StyledDocument textPaneLogData = new DefaultStyledDocument();

    public MyLogger(Class clazz) {
        myLogger = Logger.getLogger(clazz);
    }

    public void error(Object message) {
        myLogger.error(message);
        try {
            Style _styleError = textPaneLogData.addStyle("red", null);
            StyleConstants.setForeground(_styleError, Color.RED);
            textPaneLogData.insertString(textPaneLogData.getLength(), message.toString()+"\n", _styleError);
            TimeUnit.SECONDS.sleep(4);
        } catch (BadLocationException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void info(Object message) {
        myLogger.info(message);
        try {
            Style _styleSuccess = textPaneLogData.addStyle("green", null);
            StyleConstants.setForeground(_styleSuccess, Color.GREEN);
            textPaneLogData.insertString(textPaneLogData.getLength(), message.toString()+"\n", _styleSuccess);
            TimeUnit.SECONDS.sleep(2);
        } catch (BadLocationException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void debug(Object message) {
        myLogger.debug(message);
        try {
            Style _styleSuccess = textPaneLogData.addStyle("black", null);
            StyleConstants.setForeground(_styleSuccess, Color.BLACK);
            textPaneLogData.insertString(textPaneLogData.getLength(), message.toString()+"\n", _styleSuccess);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
