package net.egemsoft.updater.util;

import net.egemsoft.updater.ui.MyLogger;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by enbiya on 22.06.2016.
 */
public class MoveOldFiles2Backup {


    final static MyLogger logger = new MyLogger(MoveOldFiles2Backup.class);

    private String preVersion = "";
    private String updaterTxtUrl = "";


    public boolean execute(String srcFile, String destFile, String updaterTxtUrl, String jarFileName){

        //burada kisok altındaki jar dosyasını .superonline altındaki versiyonlar altına taşıyacak
        this.updaterTxtUrl = updaterTxtUrl;
        boolean result = false;

        getUpdaterTxt();


        // eski versiyonların tutulduğu dosya yoksa oluştur
        File oldVersionFolder = new File(destFile);
        if (!oldVersionFolder.exists()){
            oldVersionFolder.mkdir();
            logger.info("Yedekleme dosyası oluşturuldu: "+destFile);
        }

        // programın eski versiyon dosyasını oluştur
        File preVersionFolder = new File(destFile + preVersion);
        if (!preVersionFolder.exists()){
            preVersionFolder.mkdir();
            logger.info("Yedekleme dosya dizini: "+ preVersion);
        }

        logger.info("Kaynak dosya: " + srcFile + jarFileName);
        logger.info("Hedef dosya: " + destFile + jarFileName);
        logger.info("Kiosk yedekleniyor...");

        try {

            InputStream in = new FileInputStream(srcFile + jarFileName);
            OutputStream out = new FileOutputStream(destFile + preVersion + jarFileName);
            byte [] buffer = new byte[1024];
            int len ;

            while ((len = in.read(buffer)) > 0){
                out.write(buffer, 0, len);
            }

            in.close();
            out.close();

            logger.info("Dosya yedeklendi..");

            File eskiJar = new File(srcFile+jarFileName);
            if (eskiJar.exists()){
                eskiJar.delete();
                logger.info("Eski versiyon dosyası silindi!");
                result = true;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void getUpdaterTxt(){

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(updaterTxtUrl);
            HttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200){

                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                List<String> responseList = IOUtils.readLines(rd);
                if (responseList.size()>2){
                    preVersion = responseList.get(1).split("=")[1]+"\\";
                } else {
                    // internette hata olursa o günün tarihiyle açsın..
                    preVersion = new Date().toString()+"\\";
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.error("updater.txt hatası..\n"+e.toString());
        }

    }

}
