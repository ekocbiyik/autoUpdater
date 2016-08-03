package com.ekocbiyik.updaterExample.util;

import com.ekocbiyik.updaterExample.ui.MyLogger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by enbiya on 22.06.2016.
 */
public class InternetTestConnection {


    final static MyLogger logger = new MyLogger(InternetTestConnection.class);

    public boolean execute(){

        logger.info("Internet bağlantısı kontrol ediliyor.");

        boolean result = false;

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://www.ekocbiyik.com/");
            HttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200){

                logger.info("Internet bağlantısı başarılı..");
                result = true;

            } else {

                // tekrar kontrol et
                HttpResponse response1 = httpClient.execute(new HttpGet("https://www.google.com.tr/"));
                if (response1.getStatusLine().getStatusCode() == 200){

                    logger.info("Internet bağlantısı başarılı..");
                    result = true;

                } else {

                    logger.error("Internet bağlantısında hata var!");
                    JOptionPane.showMessageDialog(null, "Internet Bağlantı Hatası! \nProgram durdurulacak.");

                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    result = false;

                }

            }

        } catch (IOException e) {

            logger.error("Internet bağlantı testinde bir hata oluştu!\n");

            // tekrar kontrol et
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("https://www.google.com.tr/");
            HttpResponse response = null;

            try {
                response = httpClient.execute(httpGet);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            if (response.getStatusLine().getStatusCode() == 200){

                logger.info("Internet bağlantısı başarılı..");
                result = true;

            } else {

                logger.error("Internet bağlantısında hata var!");
                JOptionPane.showMessageDialog(null, "Internet Bağlantı Hatası! \nProgram durdurulacak.");

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }

                result = false;

            }

        }

        return result;
    }

}
