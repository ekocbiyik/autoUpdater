package net.egemsoft.updater.util;

import net.egemsoft.updater.ui.MyLogger;

import java.io.*;

/**
 * Created by enbiya on 22.06.2016.
 */
public class MoveNewFiles2Path {


    final static MyLogger logger = new MyLogger(MoveNewFiles2Path.class);

    public boolean execute(String tempFile, String kioskPath, String jarFileName){

        //temp dosyasındaki jarı program files kiosk dizinine taşıyacak
        boolean result = false;

        try {

            logger.info("Güncelleme dosyası kopyalanıyor..");
            InputStream in = new FileInputStream(tempFile + jarFileName);
            OutputStream out = new FileOutputStream(kioskPath + jarFileName);
            byte [] buffer = new byte[1024];
            int len ;

            while ((len = in.read(buffer)) > 0){
                out.write(buffer, 0, len);
            }

            in.close();
            out.close();

            logger.info("Güncelleme dosyası kopyalandı!");


            // jar dosyasını sil..
            File tmpFile = new File(tempFile + jarFileName);
            if (tmpFile.exists()){
                tmpFile.delete();
                logger.info("Indirme dosyası silindi!");
                result = true;
            }

            // temp dosyasını sil..
            File tmpFolder = new File(tempFile);
            if (tmpFolder.exists()){
                tmpFolder.delete();
                logger.info("Temp klasörü silindi!");
                result = true;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
