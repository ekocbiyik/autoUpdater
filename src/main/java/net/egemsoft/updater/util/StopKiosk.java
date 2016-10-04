package net.egemsoft.updater.util;

import net.egemsoft.updater.ui.MyLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by enbiya on 22.06.2016.
 */
public class StopKiosk {


    final static MyLogger logger = new MyLogger(StopKiosk.class);

    public boolean execute(){

        boolean result = false;

        List<Integer> pIdList = new ArrayList<Integer>();

        //find process id
        try {

            Process p = Runtime.getRuntime().exec("cmd /C tasklist");
            BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));

            String line = "";
            while ((line = bf.readLine()) != null){

                if (line.contains("Superonline Kiosk.exe")){//// TODO: 27.07.2016 buraya kiosk63.exe yi de ekle
                    logger.info(line);
                    line = line.substring("Superonline Kiosk.exe".length());
                    int taskID = Integer.parseInt(line.trim().split(" ")[0]);
                    pIdList.add(taskID);
                    logger.info("Kiosk PID: "+taskID );
                }

                if (line.contains("Superonline Kiosk 64.exe")){//// TODO: 27.07.2016 buraya kiosk63.exe yi de ekle
                    logger.info(line);
                    line = line.substring("Superonline Kiosk 64.exe".length());
                    int taskID = Integer.parseInt(line.trim().split(" ")[0]);
                    pIdList.add(taskID);
                    logger.info("Kiosk PID: "+taskID );
                }
            }

        } catch (IOException e) {
            logger.error("Kiosk durdurulurken bir hata oluştu!\n" + e.toString());
            result = false;
        }


        if (pIdList.size() > 0){

            for (int i = 0; i < pIdList.size(); i++) {

                try {

                    Process p2 = Runtime.getRuntime().exec("cmd /C taskkill /f /pid "+ pIdList.get(i));
                    logger.info("Kiosk çalışması durduruldu.");
                    result = true;

                } catch (IOException e) {
                    logger.error("Kiosk taskkill'de bir hata oluştu!\n" + e.toString());
                    result = false;
                }
            }
        } else {

            // kiosk haricinde program çalıştırılamasın
        }

        return result;
    }


}
