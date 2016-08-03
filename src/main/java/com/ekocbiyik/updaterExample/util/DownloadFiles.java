package com.ekocbiyik.updaterExample.util;

import com.ekocbiyik.updaterExample.ui.MyLogger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by enbiya on 22.06.2016.
 */
public class DownloadFiles {

    final static MyLogger logger = new MyLogger(DownloadFiles.class);

    public boolean execute(String tempFile, String fileUrl, String fileName){

        boolean result = false;

        File tmpFolder = new File(tempFile);
        if (!tmpFolder.exists()){
            tmpFolder.mkdir();
            logger.info("Temp dosyası oluşturuldu..");
        }

        try {

            logger.info("Güncelleme dosyası indiriliyor..");
            BufferedInputStream in = new BufferedInputStream(new URL(fileUrl+fileName).openStream());
            FileOutputStream out = new FileOutputStream(tempFile+fileName);

            byte [] buffer = new byte[1024];
            int len;

            while ( (len = in.read(buffer)) != -1){
                out.write(buffer, 0, len);
            }

            in.close();
            out.close();

            logger.info("Dosya indirildi..");
            result = true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            logger.info("dosya indirilirken hata oluştu..\n" +e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("dosya indirilirken hata oluştu..\n" +e.toString());
        } finally {

            return result;
        }

    }

}
