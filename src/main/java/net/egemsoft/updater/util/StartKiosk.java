package net.egemsoft.updater.util;

import net.egemsoft.updater.ui.MyLogger;

import java.io.IOException;

/**
 * Created by enbiya on 22.06.2016.
 */
public class StartKiosk {


    final static MyLogger logger = new MyLogger(StartKiosk.class);

    public boolean execute(String kioskPath){

        boolean result = false;

        try {

            //Process p = Runtime.getRuntime().exec(kioskPath + "\\Superonline Kiosk.exe");
            Process p = new ProcessBuilder(kioskPath + "Superonline Kiosk.exe","","").start();
            result = true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Kiosk programı çalıştırıldı!");

        return result;
    }

}
