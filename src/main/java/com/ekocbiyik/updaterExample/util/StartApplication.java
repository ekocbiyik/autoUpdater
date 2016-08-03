package com.ekocbiyik.updaterExample.util;

import com.ekocbiyik.updaterExample.ui.MyLogger;

import java.io.IOException;

/**
 * Created by enbiya on 22.06.2016.
 */
public class StartApplication {


    final static MyLogger logger = new MyLogger(StartApplication.class);

    public boolean execute(String kioskPath){

        boolean result = false;

        try {

            Process p = new ProcessBuilder(kioskPath + "My Application.exe","","").start();
            result = true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Program çalıştırıldı!");

        return result;
    }

}
